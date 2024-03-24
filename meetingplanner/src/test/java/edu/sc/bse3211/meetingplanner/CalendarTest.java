package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CalendarTest {
	
	@Test
	public void testAddMeeting_holiday() {
		Calendar calendar = new Calendar();	
		try {
			Meeting janan = new Meeting(2, 16, "Janan Luwum");
			calendar.addMeeting(janan);
			Boolean added = calendar.isBusy(2, 16, 0, 23);
			assertTrue("Janan Luwum Day should be marked as busy on the calendar",added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_invalidDate() throws TimeConflictException {
		// **Test Description:** This test verifies that the addMeeting method throws a TimeConflictException 
   		//                       when attempting to add a meeting with an invalid date (e.g February 30th).
		Calendar calendar = new Calendar();
		Room room = new Room("B141");
		Person sylvia = new Person("Namuli Sylvia");
		ArrayList<Person> attendee = new ArrayList<>(Arrays.asList(sylvia));
		try {
			Meeting meeting = new Meeting(2, 30, 10, 12, attendee, room, "Invalid Meeting");
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException for an invalid date.");
		} catch (TimeConflictException e) {
			assertEquals("Day does not exist.", e.getMessage());
		}
	}
	
	@Test
	public void testAddMeeting_meetingsOverlap() throws TimeConflictException {
		// **Test Description:** This test verifies that the addMeeting method throws a TimeConflictException
		//                       when attempting to add a meeting with a time that overlaps with an existing meeting 
		//                       on the same date.
		Calendar calendar = new Calendar();
		Meeting meeting1 = new Meeting(3, 15, 10, 12);
		Meeting meeting2 = new Meeting(3, 15, 11, 13); 

		try {
			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting2); 
			fail("Expected TimeConflictException for overlapping meetings");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Overlap with another item"));
		}
	}

	@Test
	public void testClearSchedule() throws TimeConflictException {
		// **Test Description:** This test verifies that the clearSchedule method successfully removes a scheduled meeting 
		//                       and the calendar is no longer marked as busy for that time slot.

		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(4, 20, 9, 10);
		try {
			calendar.addMeeting(meeting);
		} catch (TimeConflictException e) {
			fail("Unexpected exception during meeting addition: " + e.getMessage());
		}
		calendar.clearSchedule(4, 20); 
		assertFalse("Meeting should be removed", calendar.isBusy(4, 20, 9, 10));
	}


	@Test
	public void testPrintAgenda() throws TimeConflictException {
		// **Test Description:** This test verifies that the printAgenda method formats the meeting agenda correctly, 
		//                       including meeting details (date, time, room, attendees, title).

		Calendar calendar = new Calendar();
		Room room1 = new Room("B101");
		Person sylvia = new Person("Namuli Sylvia");
		ArrayList<Person> attendees1 = new ArrayList<>(Arrays.asList(sylvia));
		Meeting meeting1 = new Meeting(5, 10, 13, 14, attendees1, room1, "Meeting 1");

		calendar.addMeeting(meeting1);
		String agenda = calendar.printAgenda(5);

		String expectedOutput = "5/10, 13 - 14,B101: Meeting 1\nAttending: Namuli Sylvia"; 
		assertEquals("Meeting agenda should match expected format", expectedOutput, agenda);
	}

	// Test fails if no exception is thrown
	@Test
	public void testAddMeeting_SameStartTimeEndTime() throws TimeConflictException {
		Calendar calendar = new Calendar();
		
		Meeting meeting = new Meeting(3, 25, 10, 10);
		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException");

		} catch (TimeConflictException e) {
			assertEquals("Meeting starts before it ends.", e.getMessage());
		}
		
	}

	// Test is passed if an exception is thrown (if getMessage() is not empty)
	@Test
	public void testAddMeeting_invalidStartTime() throws TimeConflictException {
		Calendar calendar = new Calendar();
		
		Meeting meeting = new Meeting(3, 25, 26, 5);
		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException");

		} catch (TimeConflictException e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	// Test fails if an exception is not thrown
	@Test
	public void testAddMeeting_invalidEndTime() throws TimeConflictException {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(3, 25, 1, 27);
		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException");

		} catch (TimeConflictException e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	// Test fails if an assertion error is obtained
	@Test
	public void testGetMeeting() throws TimeConflictException {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(6, 25, 15, 16);

		try {
			calendar.addMeeting(meeting);
		} catch (TimeConflictException e) {
			fail("addMeeting() failed");
		}
		Meeting retrievedMeeting = calendar.getMeeting(6, 25, 0);

		assertEquals(meeting, retrievedMeeting);

	}

	@Test
	public void testRemoveMeeting() {
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(7, 12, 10, 11);
		try {
			calendar.addMeeting(meeting);
			assertTrue(calendar.isBusy(7, 12, 10, 11));
			calendar.removeMeeting(7, 12, 0);
		    assertFalse(calendar.isBusy(7, 12, 10, 11));
		} catch (TimeConflictException e) {
			fail("Remove meeting test failed");
		}
		
	}


}

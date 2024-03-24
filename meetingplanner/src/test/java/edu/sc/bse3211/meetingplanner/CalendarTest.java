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
		Room room = new Room("B101");
		Person sylvia = new Person("Namuli Sylvia");
		ArrayList<Person> attendees = new ArrayList<>(Arrays.asList(sylvia));
		Meeting meeting1 = new Meeting(5, 10, 13, 14, attendees, room, "Meeting 1");

		calendar.addMeeting(meeting1);
		String agenda = calendar.printAgenda(5);

		String expectedOutput = "5/10, 13 - 14,B101: Meeting 1\nAttending: Namuli Sylvia"; 
		assertEquals("Meeting agenda should match expected format", expectedOutput, agenda);
	}

	@Test
	public void testAddMeeting_SameStartTimeEndTime() throws TimeConflictException {
		// **Test Description:** This test verifies that the addMeeting method throws a TimeConflictException
		//                       with the correct message when attempting to add a meeting with the same start and end time.

		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(3, 25, 10, 10);

		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException for a meeting with the same start and end time");
		} catch (TimeConflictException e) {
			assertEquals("Meeting starts before it ends.", e.getMessage());
		}
	}


	@Test
	public void testAddMeeting_invalidStartTime() {
		// **Test Description:** This test verifies that the addMeeting method throws a TimeConflictException
		//                       with a descriptive message when attempting to add a meeting with an invalid start time (past midnight).
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(3, 25, 26, 5); 

		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException for invalid start time");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Meeting starts before it ends."));
		}
	}

	@Test
	public void testAddMeeting_invalidEndTime() {
		// **Test Description:** This test verifies that the addMeeting method throws a TimeConflictException
		//                       with a descriptive message when attempting to add a meeting with an invalid end time (past midnight).

		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(3, 25, 1, 27);

		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException for invalid end time");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Illegal hour."));
		}
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

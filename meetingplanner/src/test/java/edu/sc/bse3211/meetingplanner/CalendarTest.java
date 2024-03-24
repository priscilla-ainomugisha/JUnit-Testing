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
		// Create Janan Luwum holiday	
		try {
			Meeting janan = new Meeting(2, 16, "Janan Luwum");
			calendar.addMeeting(janan);
			// Verify that it was added.
			Boolean added = calendar.isBusy(2, 16, 0, 23);
			assertTrue("Janan Luwum Day should be marked as busy on the calendar",added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	// Test fails if an exception is not thrown. Also, we check whether the appropriate exception "Day does not exist" is thrown.
	@Test
	public void testAddMeeting_invalidDate() throws TimeConflictException {
		Calendar calendar = new Calendar();

		Room room = new Room("B141");
		Person john = new Person("Namuli Sylvia");
		ArrayList<Person> attendee = new ArrayList<>(Arrays.asList(john));
		try {
			Meeting meeting = new Meeting(2, 30, 10, 12,  attendee, room, "Invalid Meeting");
			calendar.addMeeting(meeting);
			fail("Should throw exception for an invalid date.");
		} catch (TimeConflictException e) {
			assertEquals("Day does not exist.", e.getMessage());
		}
	}

	// This test fails if the exception "Overlap with another meeting" is not thrown.
	@Test
	public void testAddMeeting_meetingsOverlap() throws TimeConflictException {
		// Add a meeting for 3/15, 10-12
		Calendar calendar = new Calendar();
		try {
			Meeting meeting1 = new Meeting(3, 15, 10, 12);
			calendar.addMeeting(meeting1);

			// Try to add another meeting with overlapping time, 11-13
			Meeting meeting2 = new Meeting(3, 15, 11, 13);
			calendar.addMeeting(meeting2);

			fail("Expected TimeConflictException");
			
		} catch (TimeConflictException e) {
				assertTrue(e.getMessage().contains("Overlap with another item"));
		}
	}

	// The test fails if an exception is thrown, because it means the calendar is busy and clearSchedule() failed.
	@Test
	public void testClearSchedule() throws TimeConflictException {
		try {
			Calendar calendar = new Calendar();
			Meeting meeting = new Meeting(4, 20, 9, 10);
			calendar.addMeeting(meeting);

			calendar.clearSchedule(4, 20);
			assertFalse(calendar.isBusy(4, 20, 9, 10));
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testPrintAgenda() throws TimeConflictException {
		Calendar calendar = new Calendar();

		// Rooms
		Room room1 = new Room("B101");

		// Attendees
		Person sylvia = new Person("Namuli Sylvia");
		ArrayList<Person> attendees1 = new ArrayList<>(Arrays.asList(sylvia));;

		// Create meetings using the rooms and attendees
		Meeting meeting1 = new Meeting(5, 10, 13, 14, attendees1, room1, "Meeting 1");

		// Add meetings to the calendar
		try {
			calendar.addMeeting(meeting1);
		} catch (TimeConflictException e) {
			return;
		}

		// Print the agenda for the month
		String agenda = calendar.printAgenda(5);

		// Adjust assertions based on the expected output format
		assertTrue(agenda.contains("5/10, 13 - 14,B101: Meeting 1 Attending: Namuli Sylvia"));
	}

	// Same start and end time
	@Test
	public void testAddMeeting_SameStartTimeEndTime() throws TimeConflictException {
		Calendar calendar = new Calendar();
		// starts and ends at 10AM
		Meeting meeting = new Meeting(3, 25, 10, 10);
		try {
			calendar.addMeeting(meeting);
			fail("Expected TimeConflictException");
		} catch (TimeConflictException e) {
			assertEquals("Meeting starts before it ends.", e.getMessage());
		}
		
		}

	
}

package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CalendarTest {
	private Calendar calendar;

	@Before
    public void setUp() {
        calendar = new Calendar();
    }
	
	@Test
	public void testAddMeeting_holiday() {
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

	// What happens when a meeting is scheduled on a day that is already booked? 
	@Test
	public void testAddMeetingSameDay(){
		try{
			Meeting firstMeeting = new Meeting(9, 10, "First Meeting");
			calendar.addMeeting(firstMeeting);
			assertTrue("Meeting Scheduled", calendar.isBusy(9, 10, 0, 23));
			calendar.addMeeting(new Meeting(9, 10, "Second Meeting"));
		} catch(TimeConflictException e){
			fail("Method addMeeting() has thrown the exception: " + e.getMessage());
		}
	}

	// Does the clearSchedule() method work?
	@Test
	public void testClearSchedule(){
		try{
			Meeting testMeeting = new Meeting(2,10, "Test Meeting");
			calendar.addMeeting(testMeeting);
			calendar.clearSchedule(2, 10);
			assertTrue("Date not busy",!(calendar.isBusy(2, 10, 0, 23)));
			
		} catch(TimeConflictException e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	// Does clearSchedule() output the appropriate error message when no meeting was scheduled?
	@Test
	public void testClearScheduleOnDayWithNoMeeting(){
		calendar.clearSchedule(1,4);
	}
}

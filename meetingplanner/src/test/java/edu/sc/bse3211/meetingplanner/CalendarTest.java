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

	@Test
	public void testIsBusy(){
		try{
			Meeting firstMeeting = new Meeting(9, 10, "First Meeting");
			calendar.addMeeting(firstMeeting);
			assertTrue("First meeting added to the calendar", calendar.isBusy(9, 10, 0, 23));

			// Adding another meeting on the same day
			Meeting secondMeeting = new Meeting(9, 10, "Second Meeting");
			calendar.addMeeting(secondMeeting);

		} catch(TimeConflictException e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}
}

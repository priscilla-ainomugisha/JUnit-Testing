package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class MeetingTest {
    
	// Add test methods here. 
    // You are not required to write tests for all classes.


    private Meeting meeting;
    private Person person;
    private Room room;

    @Before
    public void setUp() {
        // Initialize Meeting instance before each test
        room = new Room("Room 1");
        person = new Person("John Doe");
        meeting = new Meeting(4, 1, 10, 11, new ArrayList<>(), room, "Meeting 1");
    }

    @Test
    public void testMeetingInitialization() {
        // Test initialization of Meeting object
        assertEquals(4, meeting.getMonth());
        assertEquals(1, meeting.getDay());
        assertEquals(10, meeting.getStartTime());
        assertEquals(11, meeting.getEndTime());
        assertEquals("Meeting 1", meeting.getDescription());
        assertEquals(room, meeting.getRoom());
        assertTrue(meeting.getAttendees().isEmpty());
    }

    @Test
    public void testAddAttendee() {
        // Test adding an attendee to the meeting
        meeting.addAttendee(person);
        assertEquals(1, meeting.getAttendees().size());
        assertTrue(meeting.getAttendees().contains(person));
    }

    @Test
    public void testRemoveAttendee() {
        // Test removing an attendee from the meeting
        meeting.addAttendee(person);
        meeting.removeAttendee(person);
        assertTrue(meeting.getAttendees().isEmpty());
    }

    @Test
    public void testToString() {
        // Test toString method
        meeting.addAttendee(person);
        String expected = "4/1, 10 - 11,Room 1: Meeting 1\nAttending: John Doe";
        assertEquals(expected, meeting.toString());
    }
}

/* testMeetingInitialization() verifies that the meeting is initialized correctly.
testAddAttendee() tests adding an attendee to the meeting.
testRemoveAttendee() tests removing an attendee from the meeting.
testToString() tests the toString() method of the Meeting class to
 ensure it returns the expected string representation of the meeting
 
 pushing........
 git add A
 git commit -m "..."
 git push -u origin main*/

package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class RoomTest {
    private Room room;
    private Meeting meeting;

    @Before
    public void setUp() {
        // Initialize Room object before each test method
        room = new Room("Room1");
        assertNotNull(room); // Check if room is not null
 
        meeting = new Meeting(4, 1, 10, 11);
    }

    @Test
    public void testAddMeeting_NormalExecution() {
        try {
            room.addMeeting(meeting);
            assertTrue(room.isBusy(4, 1, 10, 11));
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }

    @Test(expected = TimeConflictException.class)
    public void testAddMeeting_TimeConflictException() throws TimeConflictException {
        room.addMeeting(meeting);
        Meeting conflictingMeeting = new Meeting(4, 35, 10, 12);
        room.addMeeting(conflictingMeeting);
    }
    
    @Test
    public void testPrintAgenda_ByMonth_NormalExecution() {
        try {
            // Add the meeting to the room
            room.addMeeting(meeting);
            
            // Call printAgenda method with month parameter
            String agenda = room.printAgenda(5);
            
            
            // Check if room ID is not null
            assertNotNull(room.getID());
            
            // Check if agenda is not null
            assertNotNull(agenda);
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }
    
    @Test
    public void testPrintAgenda_ByDay_NormalExecution() {
        try {
            // Add the meeting to the room
            room.addMeeting(meeting);
            
            // Call printAgenda method with month and day parameters
            String agenda = room.printAgenda(4, 1);
            
            // Check if room ID is not null
            assertNotNull(room.getID());
            
            // Check if agenda is not null and contains date information
            assertNotNull(agenda);
            assertTrue(agenda.contains("/"));
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }

    @Test
    public void testIsBusy_NoMeeting_NormalExecution() {
        try {
            assertFalse(room.isBusy(4, 1, 9, 10));
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }

    @Test
    public void testGetMeeting_NormalExecution() {
        try {
            room.addMeeting(meeting);
            Meeting retrievedMeeting = room.getMeeting(4, 1, 0);
            assertNotNull(retrievedMeeting);
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveMeeting_NormalExecution() {
        try {
            room.addMeeting(meeting);
            room.removeMeeting(4, 1, 0);
            assertFalse(room.isBusy(4, 1, 10, 11));
        } catch (TimeConflictException e) {
            fail("Unexpected TimeConflictException: " + e.getMessage());
        }
    }
}

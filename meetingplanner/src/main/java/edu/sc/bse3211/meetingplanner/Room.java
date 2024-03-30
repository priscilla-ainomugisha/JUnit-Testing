package edu.sc.bse3211.meetingplanner;

public class Room {
    private String ID;
    private Calendar calendar;

    /**
     * Default constructor
     */
    public Room() {
        ID = "";
        calendar = new Calendar();
    }

    /**
     * Constructor, initializes calendar and sets ID.
     */
    public Room(String ID) {
        this.ID = ID;
        calendar = new Calendar();
    }

    public String getID() {
        return ID;
    }

    /**
     * Add a meeting to the calendar.
     *
     * @throws TimeConflictException if there's a time conflict
     */
    public void addMeeting(Meeting meeting) throws TimeConflictException {
        try {
            calendar.addMeeting(meeting);
        } catch (TimeConflictException e) {
            throw new TimeConflictException("Conflict for room " + ID + ":\n" + e.getMessage());
        }
    }

    /**
     * Prints the agenda for a month.
     *
     * @return the agenda for the specified month
     */
    public String printAgenda(int month) {
        if (calendar == null) {
            return "Calendar is not initialized.";
        } else {
            return calendar.printAgenda(month);
        }
    }

    /**
     * Prints the agenda for a day.
     *
     * @return the agenda for the specified day
     */
    public String printAgenda(int month, int day) {
        if (calendar == null) {
            return "Calendar is not initialized.";
        } else {
            return calendar.printAgenda(month, day);
        }
    }

    /**
     * Checks whether a meeting is scheduled during a timeframe.
     *
     * @return true if the room is busy during the specified timeframe, false otherwise
     * @throws TimeConflictException if there's a time conflict
     */
    public boolean isBusy(int month, int day, int start, int end) throws TimeConflictException {
        return calendar.isBusy(month, day, start, end);
    }

    /**
     * Gets a particular meeting.
     *
     * @return the meeting at the specified month, day, and index
     */
    public Meeting getMeeting(int month, int day, int index) {
        return calendar.getMeeting(month, day, index);
    }

    /**
     * Removes a particular meeting.
     */
    public void removeMeeting(int month, int day, int index) {
        calendar.removeMeeting(month, day, index);
    }
}

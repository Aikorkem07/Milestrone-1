package ticketing.entities;

public class Seat {
    private int id;
    private int eventId;
    private String seatNumber;
    private boolean booked;

    public Seat(int id, int eventId, String seatNumber, boolean booked) {
        this.id = id;
        this.eventId = eventId;
        this.seatNumber = seatNumber;
        this.booked = booked;
    }

    public boolean isBooked() { return booked; }
}
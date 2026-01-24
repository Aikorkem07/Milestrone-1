package ticketing.entities;

public class Seat {
    public int id;
    public int eventId;
    public String seatNumber;
    public boolean booked;

    public Seat(int id, int eventId, String seatNumber, boolean booked) {
        this.id = id;
        this.eventId = eventId;
        this.seatNumber = seatNumber;
        this.booked = booked;
    }
}
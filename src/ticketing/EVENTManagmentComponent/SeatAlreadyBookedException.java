package ticketing.EVENTManagmentComponent;

public class SeatAlreadyBookedException extends RuntimeException {

    public SeatAlreadyBookedException(int seatNumber) {
        super("Seat " + seatNumber + " is already booked.");
    }
}
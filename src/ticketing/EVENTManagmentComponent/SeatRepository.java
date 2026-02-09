package ticketing.EVENTManagmentComponent;

import java.util.List;

public interface SeatRepository {
    void createSeatsForEvent(String eventTitle, int totalSeats);
    void bookSeat(int number);
    List<Seat> findAll();
}

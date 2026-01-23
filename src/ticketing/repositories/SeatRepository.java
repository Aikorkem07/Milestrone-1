package ticketing.repositories;

import ticketing.entities.Seat;
import java.util.List;

public interface SeatRepository {
    void create(int eventId, String seatNumber);
    List<Seat> findByEvent(int eventId);
    Seat findById(int seatId);
    void markBooked(int seatId);
}

package ticketing.repositories;

import ticketing.entities.Seat;
import java.util.List;

public interface SeatRepository {
    void reserveSeat(int seatId);
    List<Seat> getAllSeats();
    List<Seat> getAvailableSeats();
    List<Seat> getReservedSeats();
}

package ticketing.repositories.interfaces;

import ticketing.entities.Seat;
import java.util.List;

public interface SeatRepository {
    void createSeatsForEvent(String eventTitle, int totalSeats); // Создание мест для события
    void bookSeat(int number);                                    // Бронирование места
    List<Seat> findAll();                                         // Все места
}

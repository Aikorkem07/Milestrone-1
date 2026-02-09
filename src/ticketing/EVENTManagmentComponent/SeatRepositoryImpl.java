package ticketing.EVENTManagmentComponent;

import java.util.ArrayList;
import java.util.List;

public class SeatRepositoryImpl implements SeatRepository {
    private final List<Seat> seats = new ArrayList<>();

    @Override
    public void createSeatsForEvent(String eventTitle, int totalSeats) {
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    @Override
    public void bookSeat(int number) {
        seats.stream()
                .filter(s -> s.getNumber() == number && s.isAvailable())
                .findFirst()
                .ifPresent(Seat::book);
    }

    @Override
    public List<Seat> findAll() {
        return seats;
    }
}

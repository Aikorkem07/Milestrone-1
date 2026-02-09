package ticketing.EventManagmentComponent;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SeatService {
    private List<Seat> seats;

    public SeatService(List<Seat> seats) {
        this.seats = seats;
    }


    public List<Seat> getAvailableSeats() {
        return seats.stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
    }


    public List<Seat> getSeatsByCondition(Predicate<Seat> condition) {
        return seats.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }


    public void updateSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
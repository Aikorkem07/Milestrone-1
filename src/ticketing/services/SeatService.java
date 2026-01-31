package ticketing.services;

import ticketing.entities.Seat;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SeatService {
    private List<Seat> seats;

    public SeatService(List<Seat> seats) {
        this.seats = seats;
    }

    // Получение доступных мест
    public List<Seat> getAvailableSeats() {
        return seats.stream()
                .filter(Seat::isAvailable) // Lambda для фильтрации
                .collect(Collectors.toList());
    }

    // Получение мест по произвольному условию
    public List<Seat> getSeatsByCondition(Predicate<Seat> condition) {
        return seats.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    // Обновление списка мест (например, после брони)
    public void updateSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
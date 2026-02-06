package ticketing.components.reservation;

import java.util.HashSet;
import java.util.Set;

public class ReservationComponent {
    private Set<Integer> reservedSeats = new HashSet<>();

    public boolean reserveSeat(int seatNumber) {
        if (reservedSeats.contains(seatNumber)) return false;
        reservedSeats.add(seatNumber);
        return true;
    }

    public boolean isReserved(int seatNumber) {
        return reservedSeats.contains(seatNumber);
    }
}
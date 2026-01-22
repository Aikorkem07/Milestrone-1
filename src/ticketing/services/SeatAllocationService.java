package ticketing.services;

import ticketing.entities.Seat;
import ticketing.repositories.SeatRepository;

import java.util.List;

public class SeatAllocationService {

    private final SeatRepository seatRepo;

    public SeatAllocationService(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }

    public void reserveSeat(int seatId) {
        seatRepo.reserveSeat(seatId);
    }

    public void showAvailableSeats() {
        List<Seat> seats = seatRepo.getAvailableSeats();
        System.out.println("Available seats:");
        for (Seat s : seats) {
            System.out.println(s);
        }
    }

    public void showReservedSeats() {
        List<Seat> seats = seatRepo.getReservedSeats();
        System.out.println("Reserved seats:");
        for (Seat s : seats) {
            System.out.println(s);
        }
    }

    public void showAllSeats() {
        List<Seat> seats = seatRepo.getAllSeats();
        System.out.println("All seats:");
        for (Seat s : seats) {
            System.out.println(s);
        }
    }
}

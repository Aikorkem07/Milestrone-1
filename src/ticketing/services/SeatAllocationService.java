package ticketing.services;

import ticketing.entities.Seat;
import ticketing.exceptions.SeatAlreadyBookedException;
import ticketing.repositories.SeatRepository;

public class SeatAllocationService {

    private final SeatRepository seatRepo;

    public SeatAllocationService(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }

    public void reserveSeat(int seatId) {
        Seat seat = seatRepo.findById(seatId);
        if (seat.booked) {
            throw new SeatAlreadyBookedException();
        }
        seatRepo.markBooked(seatId);
    }
}
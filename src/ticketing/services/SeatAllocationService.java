package ticketing.services;

import ticketing.exceptions.SeatAlreadyBookedException;
import ticketing.repositories.SeatRepository;

public class SeatAllocationService {

    private final SeatRepository seatRepository;

    public SeatAllocationService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void reserveSeat(int seatId) {
        if (seatRepository.isSeatBooked(seatId)) {
            throw new SeatAlreadyBookedException();
        }
        System.out.println("Seat reserved successfully");
    }
}
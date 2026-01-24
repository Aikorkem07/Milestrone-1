package ticketing.services;

import ticketing.repositories.TicketRepository;

public class TicketService {

    private final TicketRepository repo;

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    public void buyTicket(int seatId, String name, String email) {
        repo.buyTicket(seatId, name, email);
    }
}
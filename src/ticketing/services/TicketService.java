package ticketing.services;

import ticketing.exceptions.InvalidTicketException;
import ticketing.repositories.TicketRepository;

public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void validateTicket(String code) {
        if (!ticketRepository.existsByCode(code)) {
            throw new InvalidTicketException();
        }
        System.out.println("Ticket is valid");
    }
}
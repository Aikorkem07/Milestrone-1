package ticketing.services;

import ticketing.entities.Ticket;
import ticketing.repositories.TicketRepository;

import java.util.List;

public class TicketService {

    private final TicketRepository ticketRepo;

    public TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    /
    public void validateTicket(String code) {
        ticketRepo.validateTicket(code);
    }


    public void showAllTickets() {
        List<Ticket> tickets = ticketRepo.getAllTickets();
        System.out.println("All tickets:");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }

    public void showUsedTickets() {
        List<Ticket> tickets = ticketRepo.getUsedTickets();
        System.out.println("Used tickets:");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }
}
package ticketing.repositories;

import ticketing.entities.Ticket;
import java.util.List;

public interface TicketRepository {
    void validateTicket(String code);
    List<Ticket> getAllTickets();
    List<Ticket> getUsedTickets();
}

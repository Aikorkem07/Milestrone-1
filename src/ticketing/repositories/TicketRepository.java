package ticketing.repositories;

public interface TicketRepository {
    void buyTicket(int seatId, String name, String email);
}

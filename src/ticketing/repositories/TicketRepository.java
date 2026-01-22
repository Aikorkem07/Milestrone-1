package ticketing.repositories;

public interface TicketRepository {
    boolean existsByCode(String code);
}

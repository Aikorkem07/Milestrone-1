package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.entities.Ticket;
import ticketing.repositories.TicketRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса ITicketRepository.
 * Работа с таблицей tickets в базе данных через JDBC.
 */
public class TicketRepositoryImpl implements TicketRepository {

    private final IDB db;

    public TicketRepositoryImpl(IDB db) {
        this.db = db;
    }

    /**
     * Проверка билета по коду.
     * Если билет существует и не использован, помечает его как использованный.
     * @param code код билета
     */
    @Override
    public void validateTicket(String code) {
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT used FROM tickets WHERE code='" + code + "'");
            if (!rs.next()) throw new RuntimeException("Ticket not found");
            if (rs.getBoolean("used")) throw new RuntimeException("Ticket already used");

            stmt.executeUpdate("UPDATE tickets SET used=true WHERE code='" + code + "'");
            System.out.println("Ticket " + code + " is valid");

        } catch (Exception e) {
            throw new RuntimeException("Failed to validate ticket: " + e.getMessage());
        }
    }

    /**
     * Получить все билеты из базы.
     * @return список всех билетов
     */
    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tickets")) {

            while (rs.next()) {
                // создаём объект Ticket из данных SQL и добавляем в список
                tickets.add(new Ticket(
                        rs.getString("code"),    // код билета
                        rs.getInt("seat_id"),    // id места
                        rs.getBoolean("used")    // статус использования
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all tickets: " + e.getMessage());
        }
        return tickets;
    }

    /**
     * Получить только использованные билеты.
     * @return список использованных билетов
     */
    @Override
    public List<Ticket> getUsedTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tickets WHERE used=true")) {

            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getString("code"),
                        rs.getInt("seat_id"),
                        true
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch used tickets: " + e.getMessage());
        }
        return tickets;
    }
}

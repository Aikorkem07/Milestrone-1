package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.repositories.TicketRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class TicketRepositoryImpl implements TicketRepository {

    private final IDB db;

    public TicketRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void buyTicket(int seatId, String name, String email) {
        try (Connection c = db.getConnection()) {

            // 1️⃣ create customer
            PreparedStatement psCustomer = c.prepareStatement(
                    "insert into customers(name, email) values (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            psCustomer.setString(1, name);
            psCustomer.setString(2, email);
            psCustomer.executeUpdate();

            ResultSet rs = psCustomer.getGeneratedKeys();
            rs.next();
            int customerId = rs.getInt(1);

            // 2️⃣ create ticket
            String code = "TCK-" + UUID.randomUUID().toString().substring(0, 4);

            PreparedStatement psTicket = c.prepareStatement(
                    "insert into tickets(seat_id, customer_id, code) values (?, ?, ?)"
            );
            psTicket.setInt(1, seatId);
            psTicket.setInt(2, customerId);
            psTicket.setString(3, code);
            psTicket.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.repositories.TicketRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TicketRepositoryImpl implements TicketRepository {

    private final IDB db;

    public TicketRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public boolean existsByCode(String code) {
        String sql = "SELECT id FROM tickets WHERE ticket_code = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

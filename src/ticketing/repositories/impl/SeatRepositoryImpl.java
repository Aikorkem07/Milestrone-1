package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.repositories.SeatRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeatRepositoryImpl implements SeatRepository {

    private final IDB db;

    public SeatRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public boolean isSeatBooked(int seatId) {
        String sql = "SELECT booked FROM seats WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, seatId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("booked");
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

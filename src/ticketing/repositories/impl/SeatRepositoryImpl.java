package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.entities.Seat;
import ticketing.repositories.SeatRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatRepositoryImpl implements SeatRepository {

    private final IDB db;

    public SeatRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void create(int eventId, String seatNumber) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("insert into seats(event_id, seat_number) values (?, ?)")) {

            ps.setInt(1, eventId);
            ps.setString(2, seatNumber);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Seat> findByEvent(int eventId) {
        List<Seat> list = new ArrayList<>();

        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("select * from seats where event_id = ?")) {

            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Seat(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getString("seat_number"),
                        rs.getBoolean("booked")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Seat findById(int seatId) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("select * from seats where id = ?")) {

            ps.setInt(1, seatId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Seat(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getString("seat_number"),
                        rs.getBoolean("booked")
                );
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void markBooked(int seatId) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("update seats set booked = true where id = ?")) {

            ps.setInt(1, seatId);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

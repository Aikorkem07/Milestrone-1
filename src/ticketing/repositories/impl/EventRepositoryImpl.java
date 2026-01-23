package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.entities.Event;
import ticketing.repositories.EventRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class EventRepositoryImpl implements EventRepository {

    private final IDB db;

    public EventRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void add(Event event) {
        String sql = "INSERT INTO events(name, event_date, cancelled) VALUES (?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, event.getName());
            // передаём LocalDateTime напрямую
            ps.setObject(2, event.getEventDate());
            ps.setBoolean(3, event.isCancelled());

            ps.execute();

            System.out.println("Event added successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Event findById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                LocalDateTime date = rs.getObject("event_date", LocalDateTime.class);
                boolean cancelled = rs.getBoolean("cancelled");
                return new Event(id, name, date, cancelled);
            }

            throw new RuntimeException("Event not found");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

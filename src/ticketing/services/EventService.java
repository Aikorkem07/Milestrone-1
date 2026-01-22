package ticketing.services;

import ticketing.data.IDB;
import ticketing.entities.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class EventService {

    private final IDB db;

    public EventService(IDB db) {
        this.db = db;
    }

    public void createEvent(String name, LocalDateTime date) {
        String sql = "INSERT INTO events(name, event_date, cancelled) VALUES (?, ?, false)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setObject(2, date); // напрямую LocalDateTime
            ps.execute();

            System.out.println("Event created successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
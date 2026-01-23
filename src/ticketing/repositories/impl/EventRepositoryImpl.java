package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.entities.Event;
import ticketing.repositories.EventRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    private final IDB db;

    public EventRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void create(Event event) {
        try (Connection c = db.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("insert into events(name, event_date) values (?, ?)")) {

            ps.setString(1, event.name);
            ps.setDate(2, Date.valueOf(event.eventDate));
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> findAll() {
        List<Event> list = new ArrayList<>();

        try (Connection c = db.getConnection();
             ResultSet rs = c.createStatement().executeQuery("select * from events")) {

            while (rs.next()) {
                list.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("event_date").toLocalDate(),
                        rs.getBoolean("cancelled")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

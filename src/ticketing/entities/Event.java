package ticketing.entities;

import java.time.LocalDate;

public class Event {
    public int id;
    public String name;
    public LocalDate eventDate;
    public boolean cancelled;

    public Event(int id, String name, LocalDate eventDate, boolean cancelled) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.cancelled = cancelled;
    }
}
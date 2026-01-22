package ticketing.entities;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private LocalDateTime eventDate;
    private boolean cancelled;

    public Event(int id, String name, LocalDateTime eventDate, boolean cancelled) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.cancelled = cancelled;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isCancelled() { return cancelled; }
}
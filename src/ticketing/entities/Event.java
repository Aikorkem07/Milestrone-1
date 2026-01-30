package ticketing.entities;

import java.time.LocalDateTime;

public class Event {
    private String title;
    private String venue;
    private LocalDateTime schedule;
    private int totalSeats;

    private Event(Builder builder) {
        this.title = builder.title;
        this.venue = builder.venue;
        this.schedule = builder.schedule;
        this.totalSeats = builder.totalSeats;
    }

    public String getTitle() { return title; }
    public String getVenue() { return venue; }
    public LocalDateTime getSchedule() { return schedule; }
    public int getTotalSeats() { return totalSeats; }

k
    public static class Builder {
        private String title;
        private String venue;
        private LocalDateTime schedule;
        private int totalSeats;

        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setVenue(String venue) { this.venue = venue; return this; }
        public Builder setSchedule(LocalDateTime schedule) { this.schedule = schedule; return this; }
        public Builder setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; return this; }

        public Event build() { return new Event(this); }
    }
}
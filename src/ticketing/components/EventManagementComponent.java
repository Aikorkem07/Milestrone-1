package ticketing.components;

import java.util.ArrayList;
import java.util.List;

public class EventManagementComponent {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public static class Event {
        private String title;
        private String venue;

        public Event(String title, String venue) {
            this.title = title;
            this.venue = venue;
        }

        public String getTitle() { return title; }
        public String getVenue() { return venue; }

        @Override
        public String toString() {
            return "Event{" + "title='" + title + '\'' + ", venue='" + venue + '\'' + '}';
        }
    }
}

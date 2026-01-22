package ticketing.services;

import ticketing.entities.Event;
import ticketing.repositories.EventRepository;
import ticketing.exceptions.EventCancelledException;

import java.time.LocalDateTime;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void createEvent(String name, LocalDateTime date) {
        Event event = new Event(0, name, date, false);
        eventRepository.add(event);
        System.out.println("Event created successfully");
    }

    public void checkEventCancelled(int eventId) {
        Event event = eventRepository.findById(eventId);
        if (event.isCancelled()) throw new EventCancelledException();
    }
}
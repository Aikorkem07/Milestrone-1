package ticketing.EventManagmentComponent;

import java.util.List;
import java.util.Optional;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public Optional<Event> getEventByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
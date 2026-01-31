package ticketing.repositories.impl;

import ticketing.entities.Event;
import ticketing.repositories.interfaces.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public Optional<Event> findByTitle(String title) {
        return events.stream()
                .filter(e -> e.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public List<Event> findAll() {
        return events;
    }
}

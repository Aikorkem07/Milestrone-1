package ticketing.repositories;

import ticketing.entities.Event;

public interface EventRepository {
    void add(Event event);
    Event findById(int id);
}

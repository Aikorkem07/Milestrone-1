package ticketing.repositories;

import ticketing.entities.Event;
import java.util.List;

public interface EventRepository {
    void create(Event event);
    List<Event> findAll();
}

package ticketing.repositories.interfaces;

import ticketing.entities.Event;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    void save(Event event);
    Optional<Event> findByTitle(String title);
    List<Event> findAll();
}

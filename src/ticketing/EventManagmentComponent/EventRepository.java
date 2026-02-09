package ticketing.EventManagmentComponent;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    void save(Event event);
    Optional<Event> findByTitle(String title);
    List<Event> findAll();
}

package ticketing.repositories.interfaces;

import ticketing.entities.Event;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    void save(Event event);                     // Сохранение события
    Optional<Event> findByTitle(String title); // Поиск события по названию
    List<Event> findAll();                      // Получение всех событий
}

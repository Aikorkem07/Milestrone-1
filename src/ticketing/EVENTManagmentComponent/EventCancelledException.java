package ticketing.EVENTManagmentComponent;

public class EventCancelledException extends RuntimeException {

    public EventCancelledException(String eventTitle) {
        super("Event '" + eventTitle + "' is cancelled.");
    }
}
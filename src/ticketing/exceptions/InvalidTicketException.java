package ticketing.exceptions;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException() {
        super("Invalid ticket code");
    }
}
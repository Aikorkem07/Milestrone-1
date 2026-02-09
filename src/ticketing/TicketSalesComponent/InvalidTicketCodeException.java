package ticketing.TicketSalesComponent;

public class InvalidTicketCodeException extends RuntimeException {

    public InvalidTicketCodeException(String code) {
        super("Invalid ticket code: " + code);
    }
}
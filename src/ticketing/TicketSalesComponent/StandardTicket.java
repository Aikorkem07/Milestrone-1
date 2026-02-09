package ticketing.TicketSalesComponent;

public class StandardTicket extends Ticket {
    public StandardTicket(int seatNumber, double price) {
        super(seatNumber, price);
    }

    @Override
    public String getType() { return "Standard"; }
}
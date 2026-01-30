package ticketing.entities;

public class StandardTicket extends Ticket {
    public StandardTicket(int seatNumber, double price) {
        super(seatNumber, price);
    }

    @Override
    public String getType() { return "Standard"; }
}
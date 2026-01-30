package ticketing.entities;

public class VIPTicket extends Ticket {
    public VIPTicket(int seatNumber, double price) {
        super(seatNumber, price);
    }

    @Override
    public String getType() { return "VIP"; }
}
package ticketing.TicketSalesComponent;

public class StudentTicket extends Ticket {
    public StudentTicket(int seatNumber, double price) {
        super(seatNumber, price);
    }

    @Override
    public String getType() { return "Student"; }
}
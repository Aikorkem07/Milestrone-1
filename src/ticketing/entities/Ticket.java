package ticketing.entities;

public class Ticket {
    private int id;
    private String ticketCode;

    public Ticket(int id, String ticketCode) {
        this.id = id;
        this.ticketCode = ticketCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }
}
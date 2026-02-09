package ticketing.TicketSalesComponent;

public class TicketFactory {


    public static Ticket createTicket(String type, int seatNumber, double price) {
        switch (type.toUpperCase()) {
            case "VIP":
                return new VIPTicket(seatNumber, price);
            case "STUDENT":
                return new StudentTicket(seatNumber, price);
            default:
                return new StandardTicket(seatNumber, price);
        }
    }
}

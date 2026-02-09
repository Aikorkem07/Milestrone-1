package ticketing.TicketSalesComponent;

import ticketing.ReportingComponent.DiscountManager;

public class TicketService {


    public Ticket buyTicket(String type, int seatNumber, double price, String customerType) {
        double finalPrice = DiscountManager.getInstance().applyDiscount(price, customerType);


        return TicketFactory.createTicket(type, seatNumber, finalPrice);
    }
}
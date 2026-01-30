package ticketing.services;

import ticketing.entities.Ticket;
import ticketing.factories.TicketFactory;
import ticketing.utils.DiscountManager;

public class TicketService {
    public Ticket buyTicket(String type, int seatNumber, double price, String customerType) {
        double finalPrice = DiscountManager.getInstance().applyDiscount(price, customerType);

        return TicketFactory.createTicket(type, seatNumber, finalPrice);
    }
}

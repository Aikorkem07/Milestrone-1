package ticketing.services;

import ticketing.entities.Ticket;
import ticketing.factories.TicketFactory;
import ticketing.utils.DiscountManager;

public class TicketService {

    // Покупка билета
    public Ticket buyTicket(String type, int seatNumber, double price, String customerType) {
        // Singleton для управления скидками
        double finalPrice = DiscountManager.getInstance().applyDiscount(price, customerType);

        // Factory для создания нужного типа билета
        return TicketFactory.createTicket(type, seatNumber, finalPrice);
    }
}
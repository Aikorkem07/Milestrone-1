package ticketing.components;

import java.util.ArrayList;
import java.util.List;

public class TicketSalesComponent {
    private List<Ticket> tickets = new ArrayList<>();

    public boolean sellTicket(Ticket ticket) {
        if (ticket.isSold()) return false;
        ticket.setSold(true);
        tickets.add(ticket);
        return true;
    }

    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    // Inner Ticket class
    public static class Ticket {
        private int seatNumber;
        private String customerName;
        private boolean sold;

        public Ticket(int seatNumber, String customerName) {
            this.seatNumber = seatNumber;
            this.customerName = customerName;
            this.sold = false;
        }

        public boolean isSold() { return sold; }
        public void setSold(boolean sold) { this.sold = sold; }

        @Override
        public String toString() {
            return "Ticket{" + "seatNumber=" + seatNumber + ", customerName='" + customerName + '\'' + ", sold=" + sold + '}';
        }
    }
}

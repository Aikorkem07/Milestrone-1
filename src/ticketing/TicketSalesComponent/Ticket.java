package ticketing.TicketSalesComponent;

public abstract class Ticket {
    protected int seatNumber;
    protected double price;

    public Ticket(int seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public int getSeatNumber() { return seatNumber; }
    public double getPrice() { return price; }
    public abstract String getType();
}
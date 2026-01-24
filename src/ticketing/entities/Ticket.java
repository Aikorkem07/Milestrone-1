package ticketing.entities;

public class Ticket {
    public int id;
    public int seatId;
    public int customerId;
    public String code;

    public Ticket(int id, int seatId, int customerId, String code) {
        this.id = id;
        this.seatId = seatId;
        this.customerId = customerId;
        this.code = code;
    }
}
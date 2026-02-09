package ticketing.TicketSalesComponent;

public class Customer {
    private String name;
    private String type; // "VIP", "Student", "Regular"

    public Customer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }
}
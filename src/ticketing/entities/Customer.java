package ticketing.entities;

public class Customer {
    private String name;
    private String type; // "VIP", "Student", "Regular" и т.д.

    public Customer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }
}
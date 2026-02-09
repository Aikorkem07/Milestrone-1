package ticketing.ReportingComponent;

public class DiscountManager {
    private static DiscountManager instance;

    private DiscountManager() {}

    public static DiscountManager getInstance() {
        if (instance == null) instance = new DiscountManager();
        return instance;
    }

    public double applyDiscount(double price, String customerType) {
        return switch (customerType.toUpperCase()) {
            case "VIP" -> price * 0.8;
            case "STUDENT" -> price * 0.9;
            default -> price;
        };
    }
}

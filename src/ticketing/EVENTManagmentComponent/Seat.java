package ticketing.EVENTManagmentComponent;

public class Seat {
    private int number;
    private boolean booked;

    public Seat(int number) {
        this.number = number;
        this.booked = false;
    }

    public int getNumber() { return number; }
    public boolean isAvailable() { return !booked; }
    public void book() { this.booked = true; }
}
import db.IDB;
import db.PostgresDB;
import entities.Event;
import entities.Customer;
import entities.Seat;
import repositories.*;
import repositories.impl.*;
import services.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== EVENT TICKETING SYSTEM START ===");

        // DB
        IDB db = new PostgresDB();
        System.out.println("✅ Connected to database");

        // Repositories
        EventRepository eventRepo = new EventRepositoryImpl(db);
        SeatRepository seatRepo = new SeatRepositoryImpl(db);
        CustomerRepository customerRepo = new CustomerRepositoryImpl(db);
        TicketRepository ticketRepo = new TicketRepositoryImpl(db);

        // Services
        SeatAllocationService seatService = new SeatAllocationService(seatRepo);
        TicketService ticketService = new TicketService(eventRepo, seatRepo, ticketRepo);

        // 1. Create Event
        Event event = new Event("Cinema Night", "2026-03-01");
        eventRepo.create(event);
        int eventId = event.getId();

        System.out.println("\n🎬 Event created: " + event.getName() + " on " + event.getDate());

        // 2. Create seats for event
        seatRepo.create(new Seat(eventId, 1));
        seatRepo.create(new Seat(eventId, 2));
        seatRepo.create(new Seat(eventId, 3));

        System.out.println("🪑 Seats created: 1, 2, 3");

        // 3. Create customer
        Customer customer = new Customer("John Doe", "john@example.com");
        customerRepo.create(customer);
        int customerId = customer.getId();

        System.out.println("👤 Customer created: " + customer.getName() +
                " (" + customer.getEmail() + ")");

        // 4. Successful seat reservation
        try {
            System.out.println("\n🪑 Reserving seat 1...");
            seatService.reserveSeat(eventId, 1);
            System.out.println("✅ Seat 1 reserved successfully");
        } catch (exceptions.SeatAlreadyBookedException e) {
            System.out.println("❌ " + e.getMessage());
        }

        // 5. Successful ticket purchase
        try {
            System.out.println("\n🎟 Buying ticket...");
            ticketService.buyTicket(eventId, 1, customerId, "CODE123");
            System.out.println("✅ Ticket purchased successfully: CODE123");
        } catch (exceptions.SeatAlreadyBookedException |
                 exceptions.EventCancelledException e) {
            System.out.println("❌ Error buying ticket: " + e.getMessage());
        }

        // 6. SeatAlreadyBookedException demo
        try {
            System.out.println("\n🪑 Reserving seat 1 again...");
            seatService.reserveSeat(eventId, 1);
        } catch (exceptions.SeatAlreadyBookedException e) {
            System.out.println("❌ Expected error: " + e.getMessage());
        }

        // 7. EventCancelledException demo
        System.out.println("\n🚫 Cancelling event...");
        eventRepo.cancelEvent(eventId);

        try {
            System.out.println("🎟 Buying ticket after cancellation...");
            ticketService.buyTicket(eventId, 2, customerId, "CODE124");
        } catch (exceptions.EventCancelledException e) {
            System.out.println("❌ Expected error: " + e.getMessage());
        } catch (exceptions.SeatAlreadyBookedException e) {
            System.out.println("❌ " + e.getMessage());
        }

        System.out.println("\n=== RUN SUCCESS ===");
    }
}

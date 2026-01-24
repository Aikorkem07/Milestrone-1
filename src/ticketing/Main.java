package ticketing;

import ticketing.data.PostgresDB;
import ticketing.entities.Event;
import ticketing.repositories.EventRepository;
import ticketing.repositories.SeatRepository;
import ticketing.repositories.TicketRepository;
import ticketing.repositories.impl.EventRepositoryImpl;
import ticketing.repositories.impl.SeatRepositoryImpl;
import ticketing.repositories.impl.TicketRepositoryImpl;
import ticketing.services.SeatAllocationService;
import ticketing.services.TicketService;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PostgresDB db = new PostgresDB();
        


        EventRepository eventRepo = new EventRepositoryImpl(db);
        SeatRepository seatRepo = new SeatRepositoryImpl(db);
        TicketRepository ticketRepo = new TicketRepositoryImpl(db);


        SeatAllocationService seatService = new SeatAllocationService(seatRepo);
        TicketService ticketService = new TicketService(ticketRepo);

        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("""
            EVENT TICKETING SYSTEM
            1. Create event
            2. View events
            3. Create seats for event
            4. View seating layout
            5. Reserve seat
            6. Buy ticket
            0. Exit
            """);

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1 -> {
                        System.out.print("Event name: ");
                        String name = sc.nextLine();
                        eventRepo.create(new Event(0, name, LocalDate.now(), false));
                        System.out.println("Event created");
                    }

                    case 2 -> eventRepo.findAll()
                            .forEach(e -> System.out.println(e.id + " | " + e.name));

                    case 3 -> {
                        System.out.print("Event ID: ");
                        int eventId = sc.nextInt();
                        System.out.print("Number of seats: ");
                        int count = sc.nextInt();
                        for (int i = 1; i <= count; i++) {
                            seatRepo.create(eventId, "A" + i);
                        }
                        System.out.println("Seats created");
                    }

                    case 4 -> {
                        System.out.print("Event ID: ");
                        int eventId = sc.nextInt();
                        seatRepo.findByEvent(eventId)
                                .forEach(s ->
                                        System.out.println(
                                                s.seatNumber + " - " +
                                                        (s.booked ? "BOOKED" : "FREE")));
                    }

                    case 5 -> {
                        System.out.print("Seat ID: ");
                        seatService.reserveSeat(sc.nextInt());
                        System.out.println("Seat reserved");
                    }

                    case 6 -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Seat ID: ");
                        int seatId = sc.nextInt();
                        ticketService.buyTicket(seatId, name, email);
                        System.out.println("Ticket bought");
                    }

                    case 0 -> {
                        System.out.println("Bye 👋");
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

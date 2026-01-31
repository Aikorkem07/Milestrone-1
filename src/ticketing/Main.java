package ticketing;

import ticketing.data.PostgresDB;
import ticketing.entities.*;
import ticketing.exceptions.SeatAlreadyBookedException;
import ticketing.repositories.impl.EventRepositoryImpl;
import ticketing.repositories.impl.SeatRepositoryImpl;
import ticketing.repositories.interfaces.EventRepository;
import ticketing.repositories.interfaces.SeatRepository;
import ticketing.services.EventService;
import ticketing.services.SeatService;
import ticketing.services.TicketService;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PostgresDB db = new PostgresDB();

        try (Connection connection = db.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected to Supabase");
            }
        } catch (Exception e) {
            System.out.println("Failed to connect to Supabase");
            e.printStackTrace();
            return;
        }

        EventRepository eventRepo = new EventRepositoryImpl();
        SeatRepository seatRepo = new SeatRepositoryImpl();

        EventService eventService = new EventService(eventRepo);
        SeatService seatService = new SeatService(seatRepo.findAll());
        TicketService ticketService = new TicketService();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nEvent Ticketing Menu:");
            System.out.println("1. Create Event");
            System.out.println("2. View Events");
            System.out.println("3. View Available Seats");
            System.out.println("4. Buy Ticket");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Event title: ");
                    String title = scanner.nextLine();
                    System.out.print("Venue: ");
                    String venue = scanner.nextLine();
                    LocalDateTime schedule = LocalDateTime.now().plusDays(1);
                    Event event = new Event.Builder()
                            .setTitle(title)
                            .setVenue(venue)
                            .setSchedule(schedule)
                            .setTotalSeats(5)
                            .build();
                    eventService.createEvent(event);
                    seatRepo.createSeatsForEvent(title, 5);
                    seatService.updateSeats(seatRepo.findAll());
                    System.out.println("Event created!");
                }
                case 2 -> {
                    List<Event> events = eventService.getAllEvents();
                    if (events.isEmpty()) {
                        System.out.println("No events found.");
                    } else {
                        events.forEach(e ->
                                System.out.println("Title: " + e.getTitle() + ", Venue: " + e.getVenue() +
                                        ", Schedule: " + e.getSchedule()));
                    }
                }
                case 3 -> {
                    List<Seat> availableSeats = seatService.getAvailableSeats();
                    if (availableSeats.isEmpty()) {
                        System.out.println("No available seats.");
                    } else {
                        availableSeats.forEach(s -> System.out.println("Seat " + s.getNumber()));
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Seat number: ");
                        int seatNum = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ticket type (STANDARD / VIP / STUDENT): ");
                        String type = scanner.nextLine();
                        System.out.print("Customer type (REGULAR / STUDENT / VIP): ");
                        String customerType = scanner.nextLine();
                        Ticket ticket = ticketService.buyTicket(type, seatNum, 50, customerType);
                        seatRepo.bookSeat(seatNum);
                        seatService.updateSeats(seatRepo.findAll());
                        System.out.println("Ticket bought successfully!");
                        System.out.println("Type: " + ticket.getType() + ", Seat: " + ticket.getSeatNumber() +
                                ", Price: " + ticket.getPrice());
                    } catch (SeatAlreadyBookedException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}

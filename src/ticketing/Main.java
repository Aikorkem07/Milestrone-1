package ticketing;

import ticketing.data.PostgresDB;
import ticketing.entities.Customer;
import ticketing.services.*;
import ticketing.repositories.impl.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var db = new PostgresDB();

        // Сервисы
        var eventService = new EventService(db); // напрямую без репозитория
        var seatService = new SeatAllocationService(new SeatRepositoryImpl(db));
        var customerRepo = new CustomerRepositoryImpl(db);
        var ticketService = new TicketService(new TicketRepositoryImpl(db));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Create event
                    2. Reserve seat
                    3. Buy ticket
                    4. Validate ticket
                    0. Exit
                    """);

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Event name: ");
                        String name = sc.nextLine();
                        System.out.print("Event date (YYYY-MM-DDTHH:MM): ");
                        String dateStr = sc.nextLine();
                        LocalDateTime date = LocalDateTime.parse(dateStr);

                        eventService.createEvent(name, date);
                    }
                    case 2 -> seatService.reserveSeat(1); // пример, резервируем сиденье с id=1
                    case 3 -> {
                        System.out.print("Customer name: ");
                        String name = sc.nextLine();
                        System.out.print("Customer email: ");
                        String email = sc.nextLine();
                        Customer customer = new Customer(name, email);
                        customerRepo.add(customer);

                        ticketService.buyTicket(customer);
                    }
                    case 4 -> {
                        System.out.print("Ticket code: ");
                        String code = sc.nextLine();
                        ticketService.validateTicket(code);
                    }
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

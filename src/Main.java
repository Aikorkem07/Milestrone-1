package ticketing;

import ticketing.data.PostgresDB;
import ticketing.repositories.impl.SeatRepositoryImpl;
import ticketing.repositories.impl.TicketRepositoryImpl;
import ticketing.services.SeatAllocationService;
import ticketing.services.TicketService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var db = new PostgresDB();

        var seatService = new SeatAllocationService(new SeatRepositoryImpl(db));
        var ticketService = new TicketService(new TicketRepositoryImpl(db));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Reserve seat
                    2. Validate ticket
                    0. Exit
                    """);

            int choice = sc.nextInt();

            try {
                if (choice == 1) seatService.reserveSeat(1);
                if (choice == 2) ticketService.validateTicket("ABC123");
                if (choice == 0) break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

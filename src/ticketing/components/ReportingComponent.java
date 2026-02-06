package ticketing.components;

import java.util.List;

public class ReportingComponent {

    public void generateReport(List<String> records) {
        System.out.println("=== Report ===");
        records.forEach(System.out::println);
        System.out.println("=== End Report ===");
    }

    public void generateEventReport(List<?> events) {
        System.out.println("=== Event Report ===");
        events.forEach(System.out::println);
        System.out.println("=== End Event Report ===");
    }
}

package ticketing.components;
import java.util.ArrayList;
import java.util.List;

public class DataComponent {
    private List<String> database = new ArrayList<>();

    public void save(String record) {
        database.add(record);
    }

    public List<String> getAll() {
        return new ArrayList<>(database);
    }
}
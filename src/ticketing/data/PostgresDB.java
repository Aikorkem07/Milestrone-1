package ticketing.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres";

    private static final String USER =
            "postgres.olpszwzwhedzckiiraxm";

    private static final String PASSWORD =
            "Aikosha.2007";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}

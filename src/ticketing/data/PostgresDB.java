package ticketing.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PostgresDB implements IDB {

    private final String url;
    private final String user;
    private final String password;

    public PostgresDB() {
        try {
            Properties props = new Properties();
            var in = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (in == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            props.load(in);

            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB config", e);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Failed to connect to Supabase");
            throw new RuntimeException(e);
        }
    }
}

package ticketing.repositories.impl;

import ticketing.data.IDB;
import ticketing.entities.Customer;
import ticketing.repositories.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final IDB db;

    public CustomerRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void add(Customer customer) {
        String sql = "INSERT INTO customers(full_name, email) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getEmail());
            ps.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

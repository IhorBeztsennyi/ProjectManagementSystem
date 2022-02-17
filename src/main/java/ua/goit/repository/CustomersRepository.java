package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.dao.CustomersDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersRepository implements Repository<CustomersDao> {

    private DataBaseManager managerDB;
    private static final String SAVE_NEW_CUSTOMER = "INSERT INTO customers (first_name, last_name, age, gender, email, phone) VALUES (?, ?, ?, ?, ?, ?);";

    public CustomersRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public void save(CustomersDao customer) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_CUSTOMER)) {
            preparedStatement.setString(1, customer.getFirst_name());
            preparedStatement.setString(2, customer.getLast_name());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.setString(4, customer.getGender());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

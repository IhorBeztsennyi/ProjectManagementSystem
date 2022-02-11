package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.Customers;
import ua.goit.model.Projects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomersRepository implements Repository<Customers> {

    private DataBaseManager managerDB;
    private static final String FIND_BY_ID = "SELECT * FROM customers c WHERE c.customer_id = ?;";
    private static final String SAVE_NEW_CUSTOMER = "INSERT INTO customers (first_name, last_name, age, gender, email, phone) VALUES (?, ?, ?, ?, ?, ?);";


    public CustomersRepository (DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public Customers getById(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomer(resultSet).orElseThrow(()-> new IllegalArgumentException(String.format("Customer with %s customer_id doesn't exist", id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Customers customer) {
        try (Connection connection = managerDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_CUSTOMER)){
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

    @Override
    public void delete(Customers customers) {

    }

    @Override
    public int update(Customers customers) {
        return 0;
    }

    private Optional<Customers> mapToCustomer (ResultSet resultSet) throws SQLException {
        Customers customer = null;
        while (resultSet.next()){
            customer = new Customers();
            customer.setCustomer_id(resultSet.getInt("customer_id"));
            customer.setFirst_name(resultSet.getString("first_name"));
            customer.setLast_name(resultSet.getString("last_name"));
            customer.setAge(resultSet.getInt("age"));
            customer.setGender(resultSet.getString("gender"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPhone(resultSet.getString("phone"));
        }
        return Optional.ofNullable(customer);
    }
}

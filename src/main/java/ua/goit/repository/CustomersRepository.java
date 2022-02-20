package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.dao.CustomersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomersRepository implements Repository<CustomersDao> {

    private DataBaseManager managerDB;
    private static final String SAVE_NEW_CUSTOMER = "INSERT INTO customers (first_name, last_name, age, gender, email, phone) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM customers WHERE customer_id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM customers WHERE customer_id = ?";
    private static final String UPDATE = "UPDATE customers SET first_name = ?, last_name = ?, age = ?, gender = ?, email = ?, phone = ?  WHERE customer_id = ?";

    public CustomersRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public void save(CustomersDao customerDao) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_CUSTOMER)) {
            preparedStatement.setString(1, customerDao.getFirst_name());
            preparedStatement.setString(2, customerDao.getLast_name());
            preparedStatement.setInt(3, customerDao.getAge());
            preparedStatement.setString(4, customerDao.getGender());
            preparedStatement.setString(5, customerDao.getEmail());
            preparedStatement.setString(6, customerDao.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomersDao> findById(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomerDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            findById(id).orElseThrow(()-> new IllegalArgumentException(String.format("Customer with customer_id %s not found", id)));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int update(Integer customer_id, CustomersDao customerDao) {
        int columnsUpdated = 0;
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            findById(customer_id).orElseThrow(()-> new IllegalArgumentException(String.format("Customer with customer_id %s not found", customer_id)));
            preparedStatement.setString(1, customerDao.getFirst_name());
            preparedStatement.setString(2, customerDao.getLast_name());
            preparedStatement.setInt(3, customerDao.getAge());
            preparedStatement.setString(4, customerDao.getGender());
            preparedStatement.setString(5, customerDao.getEmail());
            preparedStatement.setString(6, customerDao.getPhone());
            preparedStatement.setInt(7, customer_id);
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnsUpdated;
    }

    private Optional<CustomersDao> mapToCustomerDao(ResultSet resultSet) throws SQLException {
        CustomersDao customerDao = null;
        while (resultSet.next()) {
            customerDao = new CustomersDao();
            customerDao.setCustomer_id(resultSet.getInt("customer_id"));
            customerDao.setFirst_name(resultSet.getString("first_name"));
            customerDao.setLast_name(resultSet.getString("last_name"));
            customerDao.setAge(resultSet.getInt("age"));
            customerDao.setGender(resultSet.getString("gender"));
            customerDao.setEmail(resultSet.getString("email"));
            customerDao.setPhone(resultSet.getString("phone"));
        }
        return Optional.ofNullable(customerDao);
    }
}

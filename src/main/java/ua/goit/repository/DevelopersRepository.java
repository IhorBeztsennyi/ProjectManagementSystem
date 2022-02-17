package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.dao.DevelopersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DevelopersRepository implements Repository<DevelopersDao> {

    private DataBaseManager managerDB;
    private static final String SAVE_NEW_DEVELOPER = "INSERT INTO developers (first_name, last_name, age, gender, email, phone, salary) VALUES (?, ?, ?, ?, ?, ?, ?);";

    public DevelopersRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public void save(DevelopersDao dev) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_DEVELOPER)) {
            preparedStatement.setString(1, dev.getFirstName());
            preparedStatement.setString(2, dev.getLastName());
            preparedStatement.setInt(3, dev.getAge());
            preparedStatement.setString(4, dev.getGender());
            preparedStatement.setString(5, dev.getEmail());
            preparedStatement.setString(6, dev.getPhone());
            preparedStatement.setDouble(7, dev.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

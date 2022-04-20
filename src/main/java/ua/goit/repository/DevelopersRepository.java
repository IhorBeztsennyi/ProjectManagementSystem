package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.dao.DevelopersDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DevelopersRepository implements Repository<DevelopersDao> {

    private DataBaseManager managerDB;
    private static final String SAVE_NEW_DEVELOPER = "INSERT INTO developers (first_name, last_name, age, gender, email, phone, salary) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM developers WHERE name_id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM developers WHERE name_id = ?";
    private static final String UPDATE = "UPDATE developers SET first_name = ?, last_name = ?, age = ?, gender = ?, email = ?, phone = ?, salary = ?  WHERE name_id = ?;";


    public DevelopersRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Developer with name_id %s not found", id)));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int update(Integer name_id, DevelopersDao developerDao) {
        int rowUpdated = 0;
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            findById(name_id).orElseThrow(() -> new IllegalArgumentException(String.format("Developer with name_id %s not found", name_id)));
            preparedStatement.setString(1, developerDao.getFirstName());
            preparedStatement.setString(2, developerDao.getLastName());
            preparedStatement.setInt(3, developerDao.getAge());
            preparedStatement.setString(4, developerDao.getGender());
            preparedStatement.setString(5, developerDao.getEmail());
            preparedStatement.setString(6, developerDao.getPhone());
            preparedStatement.setDouble(7, developerDao.getSalary());
            preparedStatement.setInt(8, name_id);
            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public void save(DevelopersDao devDao) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_DEVELOPER)) {
            preparedStatement.setString(1, devDao.getFirstName());
            preparedStatement.setString(2, devDao.getLastName());
            preparedStatement.setInt(3, devDao.getAge());
            preparedStatement.setString(4, devDao.getGender());
            preparedStatement.setString(5, devDao.getEmail());
            preparedStatement.setString(6, devDao.getPhone());
            preparedStatement.setDouble(7, devDao.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DevelopersDao> findById(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDeveloperDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<DevelopersDao> mapToDeveloperDao(ResultSet resultSet) throws SQLException {
        DevelopersDao developerDao = null;
        while (resultSet.next()) {
            developerDao = new DevelopersDao();
            developerDao.setNameId(resultSet.getInt("name_id"));
            developerDao.setFirstName(resultSet.getString("first_name"));
            developerDao.setLastName(resultSet.getString("last_name"));
            developerDao.setAge(resultSet.getInt("age"));
            developerDao.setGender(resultSet.getString("gender"));
            developerDao.setEmail(resultSet.getString("email"));
            developerDao.setPhone(resultSet.getString("phone"));
            developerDao.setSalary(resultSet.getDouble("salary"));
        }
        return Optional.ofNullable(developerDao);
    }
}

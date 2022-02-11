package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.Developers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DevelopersRepository implements Repository<Developers>{

    private DataBaseManager managerDB;
    private static final String FIND_BY_ID = "SELECT * FROM developers d WHERE d.name_id = ?;";
    private static final String SAVE_NEW_DEVELOPER = "INSERT INTO developers (first_name, last_name, age, gender, email, phone, salary) VALUES (?, ?, ?, ?, ?, ?, ?);";


    public DevelopersRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public Developers getById(Integer id) {
       try (Connection connection = managerDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
           preparedStatement.setInt(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();
           return mapToDeveloper(resultSet).orElseThrow(()-> new IllegalArgumentException(String.format("Developer with %s id_name doesn't exist", id)));
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public void save(Developers dev) {
        try (Connection connection = managerDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_DEVELOPER)){
            preparedStatement.setString(1, dev.getFirstName());
            preparedStatement.setString(2, dev.getLastName());
            preparedStatement.setInt(3, dev.getAge());
            preparedStatement.setString(4, dev.getGender());
            preparedStatement.setString(5, dev.getEmail());
            preparedStatement.setString(6, dev.getPhone());
            preparedStatement.setDouble(7,dev.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developers dev) {

    }

    @Override
    public int update(Developers dev) {
        return 0;
    }

    private Optional<Developers> mapToDeveloper (ResultSet resultSet) throws SQLException {
        Developers developer = null;
        while (resultSet.next()){
            developer = new Developers();
            developer.setNameId(resultSet.getInt("name_id"));
            developer.setFirstName(resultSet.getString("first_name"));
            developer.setLastName(resultSet.getString("last_name"));
            developer.setAge(resultSet.getInt("age"));
            developer.setGender(resultSet.getString("gender"));
            developer.setEmail(resultSet.getString("email"));
            developer.setPhone(resultSet.getString("phone"));
            developer.setSalary(resultSet.getDouble("salary"));
        }
        return Optional.ofNullable(developer);
    }
}

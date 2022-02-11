package ua.goit.gueries;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.Developers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Queries {

    private DataBaseManager managerDB;
    private static final String SALARY_BY_PROJECT = "SELECT sum(d.salary)\n" +
            "FROM projects p\n" +
            "INNER JOIN projectstodevelopers ptd ON p.project_id = ptd.project_id\n" +
            "INNER JOIN developers d ON ptd.name_id = d.name_id\n" +
            "WHERE p.project_id = ?;";

    private static final String DEVELOPERS_BY_PROJECT ="SELECT * \n" +
            "FROM developers d \n" +
            "INNER JOIN projectstodevelopers ptd ON d.name_id = ptd.name_id \n" +
            "INNER JOIN projects p ON ptd.project_id = p.project_id \n" +
            "WHERE p.project_id = ?;";

    private static final String JAVA_DEVELOPERS ="SELECT *\n" +
            "FROM developers d\n" +
            "INNER JOIN skills s ON d.name_id = s.developer_name_id\n" +
            "WHERE s.department = 'Java';";



    public Queries(DataBaseManager connection) {
        this.managerDB = connection;
    }


    public ArrayList<Developers> getDevelopersByProjectId(Integer project_id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPERS_BY_PROJECT)) {
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Developers> result = new ArrayList<>();
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
                result.add(developer);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public Double getSumByProjectId(Integer project_id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SALARY_BY_PROJECT)) {
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Double sum = null;
            while (resultSet.next()){
                sum = resultSet.getDouble("sum");
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

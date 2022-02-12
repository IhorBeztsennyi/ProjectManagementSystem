package ua.goit.queries;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.Developers;

import java.sql.*;
import java.util.ArrayList;

public class Queries {

    private DataBaseManager managerDB;
    private static final String SALARY_BY_PROJECT = "SELECT sum(d.salary)\n" +
            "FROM projects p\n" +
            "INNER JOIN projectstodevelopers ptd ON p.project_id = ptd.project_id\n" +
            "INNER JOIN developers d ON ptd.name_id = d.name_id\n" +
            "WHERE p.project_id = ?;";

    private static final String DEVELOPERS_BY_PROJECT = "SELECT * \n" +
            "FROM developers d \n" +
            "INNER JOIN projectstodevelopers ptd ON d.name_id = ptd.name_id \n" +
            "INNER JOIN projects p ON ptd.project_id = p.project_id \n" +
            "WHERE p.project_id = ?;";

    private static final String JAVA_DEVELOPERS = "SELECT *\n" +
            "FROM developers d\n" +
            "INNER JOIN skills s ON d.name_id = s.developer_name_id\n" +
            "WHERE s.department = 'Java';";

    private static final String MIDDLE_DEVELOPERS = "SELECT * FROM developers d \n" +
            "INNER JOIN skills s ON d.name_id = s.developer_name_id \n" +
            "WHERE s.skills_level = 'Middle';";

    private static final String COUNT_DEVELOPERS = "SELECT p.begin_data, p.name, COUNT(*) as number_of_developers  \n" +
            "FROM projects p \n" +
            "INNER JOIN projectstodevelopers ptd ON p.project_id = ptd.project_id\n" +
            "INNER JOIN developers d ON ptd.name_id = d.name_id\n" +
            "WHERE p.project_id = ptd.project_id\n" +
            "GROUP BY p.project_id;";

    public Queries(DataBaseManager connection) {
        this.managerDB = connection;
    }

    public ArrayList<ProjectDateNameCount> getCountOfDevelopers() {
        try (Connection connection = managerDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_DEVELOPERS);
            ArrayList<ProjectDateNameCount> result = new ArrayList<>();
            ProjectDateNameCount project = null;
            while (resultSet.next()) {
                project = new ProjectDateNameCount();
                project.setBegin_data(resultSet.getInt("begin_data"));
                project.setName(resultSet.getString("name"));
                project.setCountOfDevelopers(resultSet.getInt("number_of_developers"));
                result.add(project);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Developers> getMiddleDevelopers() {
        try (Connection connection = managerDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVELOPERS);
            return getListOfDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Developers> getJavaDevelopers() {
        try (Connection connection = managerDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(JAVA_DEVELOPERS);
            return getListOfDevelopers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Developers> getDevelopersByProjectId(Integer project_id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPERS_BY_PROJECT)) {
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getListOfDevelopers(resultSet);
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
            while (resultSet.next()) {
                sum = resultSet.getDouble("sum");
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Developers> getListOfDevelopers(ResultSet resultSet) throws SQLException {
        ArrayList<Developers> result = new ArrayList<>();
        Developers developer = null;
        while (resultSet.next()) {
            developer = new Developers();
            developer.setNameId(resultSet.getInt("name_id"));
            developer.setFirstName(resultSet.getString("first_name"));
            developer.setLastName(resultSet.getString("last_name"));
            developer.setAge(resultSet.getInt("age"));
            developer.setGender(resultSet.getString("gender"));
            developer.setEmail(resultSet.getString("email"));
            developer.setPhone(resultSet.getString("phone"));
            developer.setSalary(resultSet.getDouble("salary"));
            result.add(developer);
        }
        return result;
    }
}
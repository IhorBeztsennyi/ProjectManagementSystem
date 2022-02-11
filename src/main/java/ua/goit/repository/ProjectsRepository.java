package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.Developers;
import ua.goit.model.Projects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProjectsRepository implements Repository<Projects>{

    private DataBaseManager managerDB;
    private static final String FIND_BY_ID = "SELECT * FROM projects p WHERE p.project_id = ?;";
    private static final String SAVE_NEW_PROJECT = "INSERT INTO projects (name, customer_id, company_id, begin_data) VALUES (?, ?, ?, ?);";


    public ProjectsRepository (DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public Projects getById(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProject(resultSet).orElseThrow(()-> new IllegalArgumentException(String.format("Project with %s project_id doesn't exist", id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Projects project) {
        try (Connection connection = managerDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_PROJECT)){
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCustomer_id());
            preparedStatement.setInt(3, project.getCompany_id());
            preparedStatement.setInt(4, project.getBegin_data());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Projects projects) {

    }

    @Override
    public int update(Projects projects) {
        return 0;
    }

    private Optional<Projects> mapToProject (ResultSet resultSet) throws SQLException {
        Projects project = null;
        while (resultSet.next()){
            project = new Projects();
            project.setProject_id(resultSet.getInt("project_id"));
            project.setName(resultSet.getString("name"));
            project.setCustomer_id(resultSet.getInt("customer_id"));
            project.setCompany_id(resultSet.getInt("company_id"));
            project.setBegin_data(resultSet.getInt("begin_data"));
        }
        return Optional.ofNullable(project);
    }
}

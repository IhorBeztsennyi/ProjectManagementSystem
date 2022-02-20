package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.dao.ProjectsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private DataBaseManager managerDB;
    private static final String SAVE_NEW_PROJECT = "INSERT INTO projects (name, customer_id, company_id, begin_data) VALUES (?, ?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM projects WHERE project_id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM projects WHERE project_id = ?";
    private static final String UPDATE = "UPDATE projects SET name = ?, customer_id = ?, company_id = ?, begin_data = ?  WHERE project_id = ?";

    public ProjectsRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public void save(ProjectsDao projectsDao) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_PROJECT)) {
            preparedStatement.setString(1, projectsDao.getName());
            preparedStatement.setInt(2, projectsDao.getCustomer_id());
            preparedStatement.setInt(3, projectsDao.getCompany_id());
            preparedStatement.setInt(4, projectsDao.getBegin_data());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ProjectsDao> findById(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProjectDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void remove(Integer id) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Project with project_id %s not found", id)));
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int update(Integer project_id, ProjectsDao projectsDao) {
        int columnsUpdated = 0;
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            findById(project_id).orElseThrow(() -> new IllegalArgumentException(String.format("Project with project_id %s not found", project_id)));
            preparedStatement.setString(1, projectsDao.getName());
            preparedStatement.setInt(2, projectsDao.getCustomer_id());
            preparedStatement.setInt(3, projectsDao.getCompany_id());
            preparedStatement.setInt(4, projectsDao.getBegin_data());
            preparedStatement.setInt(5, projectsDao.getProject_id());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnsUpdated;
    }

    private Optional<ProjectsDao> mapToProjectDao(ResultSet resultSet) throws SQLException {
        ProjectsDao projectDao = null;
        while (resultSet.next()) {
            projectDao = new ProjectsDao();
            projectDao.setProject_id(resultSet.getInt("project_id"));
            projectDao.setName(resultSet.getString("name"));
            projectDao.setCustomer_id(resultSet.getInt("customer_id"));
            projectDao.setCompany_id(resultSet.getInt("company_id"));
            projectDao.setBegin_data(resultSet.getInt("begin_data"));
        }
        return Optional.ofNullable(projectDao);
    }
}

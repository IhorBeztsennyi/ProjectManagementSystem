package ua.goit.repository;

import ua.goit.configuration.DataBaseManager;
import ua.goit.model.Projects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectsRepository implements Repository<Projects> {

    private DataBaseManager managerDB;
    private static final String SAVE_NEW_PROJECT = "INSERT INTO projects (name, customer_id, company_id, begin_data) VALUES (?, ?, ?, ?);";

    public ProjectsRepository(DataBaseManager connection) {
        this.managerDB = connection;
    }

    @Override
    public void save(Projects project) {
        try (Connection connection = managerDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_PROJECT)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCustomer_id());
            preparedStatement.setInt(3, project.getCompany_id());
            preparedStatement.setInt(4, project.getBegin_data());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

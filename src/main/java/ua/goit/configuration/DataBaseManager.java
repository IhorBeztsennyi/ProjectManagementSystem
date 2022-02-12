package ua.goit.configuration;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseManager {
    Connection getConnection() throws SQLException;
}

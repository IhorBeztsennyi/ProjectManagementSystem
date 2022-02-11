package ua.goit.configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface DataBaseManager {

    Connection getConnection() throws SQLException;
}

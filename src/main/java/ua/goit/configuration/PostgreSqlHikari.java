package ua.goit.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSqlHikari implements DataBaseManager {

    private final HikariDataSource dataSource;

    public PostgreSqlHikari(String hostname, int port, String databaseName, String username, String password, String jdbcDriver) {
        HikariConfig config = new HikariConfig();
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error loading postgres driver", ex);
        }
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", hostname, port, databaseName));
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(100);
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

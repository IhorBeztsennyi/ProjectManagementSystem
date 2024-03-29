package ua.goit.configuration;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private Properties properties;

    public PropertiesUtil(ServletContext context) {
        this.properties = new Properties();
        try (InputStream inputStream = context.getResourceAsStream("/WEB-INF/resources/resources.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PropertiesUtil() {
        this.properties = new Properties();
        try (InputStream inputStream = new  FileInputStream("src/main/webapp/WEB-INF/resources/resources.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHostname(){
        return properties.getProperty("database.hostname");
    }

    public Integer getPort() {
        return Integer.parseInt(properties.getProperty("database.port"));
    }

    public String getSchema() {
        return properties.getProperty("database.schema");
    }

    public String getUser() {
        return properties.getProperty("database.user");
    }

    public String getPassword() {
        return properties.getProperty("database.password");
    }

    public String getJdbcDriver() {
        return properties.getProperty("jdbc.driver");
    }
}

package com.ew;

import com.ew.service.MenuService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Task start.");
        DataSource dataSource = createDataSource();
        Flyway flyway = createFlyway(dataSource);
        flyway.migrate();
        MenuService menuService = new MenuService(dataSource);
        try {
            menuService.processMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Flyway createFlyway(DataSource dataSource) {
        return Flyway.configure().dataSource(dataSource).load();
    }

    private static DataSource createDataSource() throws IOException {
        Properties properties = loadProperties();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUsername(properties.getProperty("jdbc.username"));
        config.setPassword(properties.getProperty("jdbc.password"));

        return new HikariDataSource(config);
    }

    private static Properties loadProperties() throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}

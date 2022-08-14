package com.ew.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    protected static HikariDataSource dataSource;

    @BeforeAll
    public static void init() {
        HikariConfig config = new HikariConfig();
        config.setPassword("sa");
        config.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL");
        config.setUsername("sa");
        config.setDriverClassName("org.h2.Driver");
        dataSource = new HikariDataSource(config);
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }
}


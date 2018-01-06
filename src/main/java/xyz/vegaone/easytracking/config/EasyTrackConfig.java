package xyz.vegaone.easytracking.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EasyTrackConfig {
    @Autowired
    private SpringProperties springProperties;

    @Bean
    @LiquibaseDataSource
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(springProperties.getDataSource().getJdbcUrl());
        hikariDataSource.setUsername(springProperties.getDataSource().getUsername());
        hikariDataSource.setPassword(springProperties.getDataSource().getPassword());

        return hikariDataSource;
    }
}

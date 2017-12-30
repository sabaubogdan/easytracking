package xyz.vegaone.easytracking.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EasyTrackConfig {

    @Bean
    @LiquibaseDataSource
    public DataSource dataSource() {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:easytrack");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }

}
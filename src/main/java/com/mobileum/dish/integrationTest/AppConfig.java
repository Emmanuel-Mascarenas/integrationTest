package com.mobileum.dish.integrationTest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

    @Bean
    DataSource dataSourceProvider() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://dcfmsds.cluster-cyavyaeuoral.us-west-2.rds.amazonaws.com:5432/dcfms");
        dataSource.setUsername("rddnuwldat");
        dataSource.setPassword("S7S82H8JS9DCNCB8");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

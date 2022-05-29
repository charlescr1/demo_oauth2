package com.example.demo.oauth.demooauth;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    Environment env;

    @Primary
    @Bean(name = "DataSource")
    public DataSource dataSource() throws NamingException
    {

        Properties dsProps = new Properties();
        dsProps.put("url", this.env.getProperty("spring.datasource.url"));
        dsProps.put("user", this.env.getProperty("spring.datasource.username"));
        dsProps.put("password", this.env.getProperty("spring.datasource.password"));

        Properties configProps = new Properties();
        configProps.put("dataSourceClassName", env.getProperty("spring.datasource.dataSourceClassName"));
        configProps.put("poolName", env.getProperty("spring.datasource.poolName"));
        configProps.put("maximumPoolSize", env.getProperty("spring.datasource.maximumPoolSize"));

        if (!env.getProperty("spring.datasource.minimumIdle").isEmpty())
        {
            configProps.put("minimumIdle", env.getProperty("spring.datasource.minimumIdle"));
        }

        configProps.put("maxLifetime", env.getProperty("spring.datasource.connectionTimeout"));
        configProps.put("connectionTimeout", env.getProperty("spring.datasource.connectionTimeout"));
        configProps.put("idleTimeout", env.getProperty("spring.datasource.idleTimeout"));
        configProps.put("dataSourceProperties", dsProps);

        HikariConfig hc = new HikariConfig(configProps);
        HikariDataSource ds = new HikariDataSource(hc);

        return ds;
    }
}
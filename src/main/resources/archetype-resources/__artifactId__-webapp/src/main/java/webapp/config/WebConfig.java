package ${package}.webapp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.orm.jpa.JpaTransactionManager;

import org.flywaydb.core.Flyway;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration class. Add Spring Beans here.
 */
@Configuration
@ComponentScan({"${package}.webapp.controller",})
@PropertySources({
        @PropertySource(value = "classpath:config/database.properties"),
        @PropertySource(value = "classpath:config/secrets.properties"),
})
public class WebConfig {

    // Add your Spring Beans here...

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        final String hostName = environment.getRequiredProperty("db.host");
        final Integer port = environment.getRequiredProperty("db.port", Integer.class);
        final String dbName = environment.getRequiredProperty("db.name");
        final String username = environment.getRequiredProperty("db.username");
        final String password = environment.getRequiredProperty("db.password");
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://" + hostName + ":" + port + "/" + dbName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean(initMethod = "migrate")
    @Autowired
    Flyway flyway(DataSource ds) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setLocations("classpath:migration");
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("${package}.models");
        factoryBean.setDataSource(ds);
        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
        properties.setProperty("hibernate.show_sql", "true"); // TODO: Take out in production
        properties.setProperty("format_sql", "true"); // TODO: Take out in production
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
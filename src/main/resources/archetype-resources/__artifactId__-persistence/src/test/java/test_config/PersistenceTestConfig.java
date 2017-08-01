package ${package}.test_config;

import org.hsqldb.jdbc.JDBCDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
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
@ComponentScan({"${package}.persistence", })
@EnableTransactionManagement
public class PersistenceTestConfig {

    // Add your Spring Beans here...

    @Bean
    public DataSource dataSource() {
        final SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(JDBCDriver.class);
        ds.setUrl("jdbc:hsqldb:mem:${artifactId}");
        ds.setUsername("ha");
        ds.setPassword("");

        return ds;
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
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("format_sql", "true");
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager(
            final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
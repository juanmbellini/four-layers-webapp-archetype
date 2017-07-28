package ${package}.test_config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class. Add Spring Beans here.
 */
@Configuration
@ComponentScan({"${package}.models",})
public class ModelsTestConfig {

    // Add your Spring Beans here...
}
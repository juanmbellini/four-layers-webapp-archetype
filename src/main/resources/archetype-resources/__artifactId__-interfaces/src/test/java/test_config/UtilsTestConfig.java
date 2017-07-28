package ${package}.test_config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class. Add Spring Beans here.
 */
@Configuration
@ComponentScan({"${package}.utils",})
public class UtilsTestConfig {

    // Add your Spring Beans here...
}
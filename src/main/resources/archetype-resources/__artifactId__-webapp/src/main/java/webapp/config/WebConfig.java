package ${package}.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class. Add Spring Beans here.
 */
@Configuration
@ComponentScan({"${package}.webapp.controller",})
public class WebConfig {

    // Add your Spring Beans here...
}
package ${package}.webapp.config;

import ${package}.webapp.controller.HelloWorldController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Field;
import java.net.URI;

/**
 * Configuration class. Add Spring Beans here.
 */
@Configuration
@ComponentScan({"${package}.webapp",})
public class TestConfig {

    // Add your Spring Beans here...


    public static final String MOCKED_URL = "http://localhost:8080/test";

    @Bean
    public HelloWorldController helloWorldController() {
        final UriInfo uriInfo = Mockito.mock(UriInfo.class);
        Mockito.when(uriInfo.getAbsolutePath())
                .thenReturn(URI.create(MOCKED_URL));

        final HelloWorldController helloWorldController = new HelloWorldController();
        try {
            Field uriInfoField = helloWorldController.getClass().getDeclaredField("uriInfo");
            uriInfoField.setAccessible(true);
            uriInfoField.set(helloWorldController, uriInfo);
            uriInfoField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return helloWorldController;
    }
}
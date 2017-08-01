package ${package}.test_config;

import ${package}.interfaces.SampleDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.mockito.Mockito;


/**
 * Configuration class. Add Spring Beans here.
 */
@Configuration
@ComponentScan({"${package}.services",})
public class ServicesTestConfig {

    @Bean
    public SampleDao sampleDao() {
        final SampleDao sampleDao = Mockito.mock(SampleDao.class);
        return sampleDao;
    }
}
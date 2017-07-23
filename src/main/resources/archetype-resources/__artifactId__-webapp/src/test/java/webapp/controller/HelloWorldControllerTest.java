package ${package}.webapp.controller;

import ${package}.webapp.config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.net.URI;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController helloWorldController;

    @Test
    public void testGetHelloWorld() {
        Response response = helloWorldController.getHelloWorld();
        Assert.assertEquals(HelloWorldController.HELLO_WORLD, response.getEntity());
        Assert.assertEquals(URI.create(TestConfig.MOCKED_URL), response.getLocation());
    }
}

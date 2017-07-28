package ${package}.utils;

import ${package}.test_config.UtilsTestConfig;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.Assert;

/**
 * A sample test for a sample util class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UtilsTestConfig.class)
public class SampleUtilClassTest {

    // Add utils methods tests here...

    @Test
    public void dummyTest() {
        Assert.assertTrue("Must be true!", true);
    }

}
package ${package}.interfaces.utils;

import ${package}.interfaces.test_config.TestConfig;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.Assert;

/**
 * A sample test for a sample util class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SampleUtilClassTest {

    // Add utils methods tests here...

    @Test
    public void dummyTest() {
        Assert.assertTrue("Must be true!", true);
    }

}
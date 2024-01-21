package cn.net.fasttest.runner;

import cn.net.fasttest.runner.testng.TestNGRunner;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

/**
 * @author bing
 * @create 2024/01/21
 */
public class TestNGRunnerTest {

    @Test
    public void emptyTest() {
        System.out.println("empty test ...");
        Assertions.assertTrue(true);
    }

    @Test
    public void testRun() {
        new TestNGRunner().run(this.getClass(), "emptyTest");
        Assertions.assertTrue(true);
    }
}

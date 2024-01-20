package cn.net.fasttest.runner;

import cn.net.fasttest.runner.junit.JunitRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bing
 * @create 2024/01/20
 */
public class JunitRunnerTest {

    @Test
    public void emptyTest() {
        System.out.println("empty test ...");
        Assertions.assertTrue(true);
    }

    @Test
    public void testRun() {
        new JunitRunner().run(this.getClass(), "emptyTest");
        Assertions.assertTrue(true);
    }
}

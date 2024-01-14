package cn.net.fasttest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class FastSpringTests {

    @Test
    public void test() {

        System.out.println("test...");
        Assertions.assertTrue(true);
    }

    @Test
    public void testException() {
        System.out.println("test...");
        Assertions.assertTrue(false);
    }

    public static void main(String[] args) {
        FastSpringTest.run();
    }
}

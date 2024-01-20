package cn.net.fasttest.runner;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public interface TestRunner {

    /**
     *  run test case
     * @param clazz run class
     * @param method run method
     */
    void run(Class<?> clazz, String method);
}

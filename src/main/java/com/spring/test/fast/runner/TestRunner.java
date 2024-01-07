package com.spring.test.fast.runner;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public interface TestRunner {

    /**
     *  run test case
     * @param clazz
     * @param method
     */
    void run(Class<?> clazz, String method);
}

package cn.net.fasttest.loader;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public interface HotLoadClassLoader {

    /**
     *  hot load test class
     * @param testClassName class name
     * @return clazz
     */
    Class<?> loadTestClass(String testClassName);
}

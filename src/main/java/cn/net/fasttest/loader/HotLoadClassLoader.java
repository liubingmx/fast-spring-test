package cn.net.fasttest.loader;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public interface HotLoadClassLoader {

    Class<?> loadTestClass(String testClassName);
}

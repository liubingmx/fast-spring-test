package com.spring.test.fast.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class DefaultHotClassLoader extends ClassLoader implements HotLoadClassLoader{
    @Override
    public Class<?> loadTestClass(String testClassName) {
        String fileName = testClassName.replaceAll("\\.", "/") + ".class";
        fileName = (getClass().getResource("/") + fileName).substring(5); // 判断class文件修改时间使用，substring(6)去掉开头的file:/
        try {
            byte[] data = Files.readAllBytes(Path.of(fileName));
            return defineClass(testClassName, data, 0, data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

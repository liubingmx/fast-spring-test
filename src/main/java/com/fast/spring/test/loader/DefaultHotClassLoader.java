package com.fast.spring.test.loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class DefaultHotClassLoader  implements HotLoadClassLoader{

    @Override
    public Class<?> loadTestClass(String testClassName) {
        try {
            return new InnerHotLoadClassLoader(testClassName).loadClass(testClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static class InnerHotLoadClassLoader extends ClassLoader{

        private String loadClassName;

        public InnerHotLoadClassLoader(String loadClassName) {
            this.loadClassName = loadClassName;
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (!name.equals(loadClassName)) {
                return Thread.currentThread().getContextClassLoader().loadClass(name);
            }
            return findClass(name);
        }

        @Override
        public Class<?> findClass(String testClassName) {
            if (testClassName == null) {
                return null;
            }
            String fileName = testClassName.replaceAll("\\.", "/") + ".class";
            fileName = (getClass().getResource("/") + fileName).substring(5);
            try {
                byte[] data = Files.readAllBytes(Path.of(fileName));
                return defineClass(testClassName, data, 0, data.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

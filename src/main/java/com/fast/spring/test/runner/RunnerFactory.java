package com.fast.spring.test.runner;

import com.fast.spring.test.runner.junit.JunitRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class RunnerFactory {

    private static Map<String, TestRunner> runnerMap = new ConcurrentHashMap<>();

    static {
        register("org.junit.jupiter.api.Test", new JunitRunner());
        register("org.junit..Test", new JunitRunner());
    }

    public static void register(String name, TestRunner runner) {
        runnerMap.put(name, runner);
    }

    public static  <T extends Annotation> Class<? extends Annotation> getClassByName(String className) throws ClassNotFoundException {
        Class<?> rawClass = Class.forName(className);
        if (!rawClass.isAnnotation()) {
            throw new IllegalArgumentException("Class is not an annotation: " + className);
        }
        return rawClass.asSubclass(Annotation.class);
    }

    public static Set<String> getRunnerNames() {
        return runnerMap.keySet();
    }

    public static TestRunner getRunner(String methodName, Class<?> clazz) {
        Set<String> runnerNames = runnerMap.keySet();
        for (String runnerName : runnerNames) {
            try {
                Class<? extends Annotation> testAnnotationClazz = getClassByName(runnerName);
                Annotation testAnnotation = clazz.getAnnotation(testAnnotationClazz);
                if (Objects.nonNull(testAnnotation)) {
                    return runnerMap.get(runnerName);
                }
                if (methodName != null) {
                    Method method = clazz.getDeclaredMethod(methodName);
                    testAnnotation = method.getAnnotation(testAnnotationClazz);
                    if (Objects.nonNull(testAnnotation)) {
                        return runnerMap.get(runnerName);
                    }
                }
            } catch (ClassNotFoundException e) {
                continue;
            }catch (NoSuchMethodException | ClassCastException e){
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("not support this test engine");
    }
}

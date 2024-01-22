package cn.net.fasttest.event;

import cn.net.fasttest.exception.FastTestException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class EventBus {

    private static List<FastSpringTestListener> listeners = new ArrayList<>();

    public static synchronized void addListener(FastSpringTestListener fastSpringTestListener) {
        listeners.add(fastSpringTestListener);
    }

    public static void publishEvent(FastSpringTestEvent event) {
        for (FastSpringTestListener listener : listeners) {
            // 获取指定方法上的注解
            Subscribe annotation;
            try {
                Method method = listener.getClass().getMethod("listen", FastSpringTestEvent.class);
                annotation = method.getAnnotation(Subscribe.class);
            } catch (NoSuchMethodException e) {
                throw new FastTestException(e.getMessage());
            }
            if (annotation !=null && !event.getEvent().equals(annotation.value())) {
                continue;
            }
            listener.listen(event);
        }
    }
}

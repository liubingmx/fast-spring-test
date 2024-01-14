package com.fast.spring.test.event;

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

    public static synchronized void removeListener(FastSpringTestListener fastSpringTestListener) {
        listeners.remove(fastSpringTestListener);
    }

    public static void publishEvent(FastSpringTestEvent event) {
        for (FastSpringTestListener listener : listeners) {
            listener.listen(event);
        }
    }
}

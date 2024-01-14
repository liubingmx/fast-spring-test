package com.fast.spring.test.event;

import java.util.EventListener;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public interface FastSpringTestListener extends EventListener {

    void listen(FastSpringTestEvent event);
}

package cn.net.fasttest.event;

import java.util.EventListener;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public interface FastSpringTestListener extends EventListener {

    /**
     *  This method is called when the subscribed event occurs
     * @param event event Object
     */
    void listen(FastSpringTestEvent event);
}

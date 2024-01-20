package cn.net.fasttest.event;

import java.util.EventObject;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class FastSpringTestEvent extends EventObject {

    private EventEnum event;

    /**
     * Constructs a prototypical Event.
     *
     * @param event event
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FastSpringTestEvent(EventEnum event,Object source) {
        super(source);
        this.event = event;
    }

    public EventEnum getEvent() {
        return event;
    }
}

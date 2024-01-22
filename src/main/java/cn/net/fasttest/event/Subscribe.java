package cn.net.fasttest.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author bing
 * @create 2024/01/22
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    EventEnum value();
}

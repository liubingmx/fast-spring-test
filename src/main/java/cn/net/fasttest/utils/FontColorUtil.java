package cn.net.fasttest.utils;

import java.util.Objects;

/**
 * @author bing
 * @create 2024/01/14
 */
public class FontColorUtil {

    private static final String RESET = "\033[0m";
    public static String BULE = "\033[1;34m";
    public static String RED = "\033[1;31m";


    public static String format(String content, String color) {
        if (Objects.isNull(color)) {
            return content;
        }
        return color + content + RESET;
    }
}

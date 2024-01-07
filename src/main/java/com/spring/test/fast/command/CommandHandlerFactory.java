package com.spring.test.fast.command;

import com.spring.test.fast.exception.FastTestException;
import com.spring.test.fast.loader.DefaultHotClassLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class CommandHandlerFactory {

    private static List<CommandHandler> handlers = new ArrayList<>();

    static {
        RunCommandHandler runCommandHandler = new RunCommandHandler(new DefaultHotClassLoader());
        register(runCommandHandler);
    }

    public static void register(CommandHandler commandHandler) {
        handlers.add(commandHandler);
    }

    public static CommandHandler getCommandHandler(String commandName) {
        return handlers.stream()
                .filter(e -> e.getCommand().getName().equals(commandName))
                .findFirst()
                .orElseThrow(() -> new FastTestException("not support this command 【" + commandName + "】"));

    }
}

package com.fast.spring.test.command;

import com.fast.spring.test.command.handler.ReRunCommandHandler;
import com.fast.spring.test.command.handler.RunCommandHandler;
import com.fast.spring.test.command.handler.ShowCommandHandler;
import com.fast.spring.test.event.EventBus;
import com.fast.spring.test.exception.FastTestException;
import com.fast.spring.test.loader.DefaultHotClassLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class CommandHandlerFactory {

    private static List<CommandHandler> handlers = new ArrayList<>();

    static {
        RunCommandHandler runCommandHandler = new RunCommandHandler();
        ShowCommandHandler showCommandHandler = new ShowCommandHandler();
        ReRunCommandHandler reRunCommandHandler = new ReRunCommandHandler();
        register(runCommandHandler);
        register(showCommandHandler);
        register(reRunCommandHandler);
        EventBus.addListener(showCommandHandler);
        EventBus.addListener(reRunCommandHandler);
    }

    public static void register(CommandHandler commandHandler) {
        handlers.add(commandHandler);
    }

    public static CommandHandler getCommandHandler(String commandName) {
        return handlers.stream()
                .filter(e -> e.getCommand().getName().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow(() -> new FastTestException("【 " + commandName + " 】This command is not currently supported"));

    }
}

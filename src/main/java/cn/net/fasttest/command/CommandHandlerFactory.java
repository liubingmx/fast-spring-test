package cn.net.fasttest.command;

import cn.net.fasttest.command.handler.ReRunCommandHandler;
import cn.net.fasttest.event.EventBus;
import cn.net.fasttest.exception.FastTestException;
import cn.net.fasttest.command.handler.RunCommandHandler;
import cn.net.fasttest.command.handler.ShowCommandHandler;

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

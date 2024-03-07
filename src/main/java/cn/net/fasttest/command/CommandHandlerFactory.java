package cn.net.fasttest.command;

import cn.net.fasttest.command.handler.*;
import cn.net.fasttest.event.EventBus;
import cn.net.fasttest.exception.FastTestException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bing
 * @create 2024/01/07
 */
public class CommandHandlerFactory {

    private static List<CommandHandler> handlers = new ArrayList<>();

    public static void init() {
        ShowCommandHandler showCommandHandler = new ShowCommandHandler();
        ReRunCommandHandler reRunCommandHandler = new ReRunCommandHandler();
        HistoryCommandHandler historyCommandHandler = new HistoryCommandHandler();
        register(new RunCommandHandler());
        register(showCommandHandler);
        register(reRunCommandHandler);
        register(historyCommandHandler);
        register(new HelpCommandHandler());
        EventBus.addListener(showCommandHandler);
        EventBus.addListener(reRunCommandHandler);
        EventBus.addListener(historyCommandHandler);
        register(new QuitCommandHandler());
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

    public static List<CommandHandler> getAllCommandHandlers() {
        return handlers;
    }
}

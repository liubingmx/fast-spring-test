package cn.net.fasttest.command;

import cn.net.fasttest.command.handler.HelpCommandHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bing
 * @create 2024/01/23
 */
public class CommandHandlerTest {


    @Test
    public void testFactory() {
        CommandHandlerFactory.init();
        CommandHandler run = CommandHandlerFactory.getCommandHandler("run");
        Assertions.assertEquals(run.getCommand().getName(), "run");

        HelpCommandHandler helpCommandHandler = new HelpCommandHandler();
        CommandHandlerFactory.register(helpCommandHandler);
        CommandHandler commandHandler = CommandHandlerFactory.getCommandHandler(helpCommandHandler.getCommand().getName());
        Assertions.assertEquals(helpCommandHandler.getCommand().getName(), commandHandler.getCommand().getName());
    }
}

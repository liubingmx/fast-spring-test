package cn.net.fasttest.command.handler;

import cn.net.fasttest.command.Command;
import cn.net.fasttest.command.CommandHandler;
import cn.net.fasttest.command.CommandHandlerFactory;
import cn.net.fasttest.utils.FontColorUtil;

import java.sql.SQLOutput;
import java.util.List;

/**
 * @author bing
 * @create 2024/01/21
 */
public class HelpCommandHandler implements CommandHandler {
    @Override
    public Command getCommand() {
        return new Command("help", "Print help information for all commands");
    }

    @Override
    public void run(Command command) {
        System.out.println("--------------------------HELP INFO--------------------------");
        List<CommandHandler> allCommandHandlers = CommandHandlerFactory.getAllCommandHandlers();
        for (CommandHandler commandHandler : allCommandHandlers) {
            System.out.printf("%s : %s \n", FontColorUtil.format(commandHandler.getCommand().getName(), FontColorUtil.BULE),
                    commandHandler.getCommand().getDescription());

            System.out.println();
        }
        System.out.println("------------------------DEFAULT COMMAND------------------------");
        System.out.println("If you enter the command directly and it is not the above command, the run command will be executed by default. " +
                "For example\n cn.net.fasttest.FastSpringTests#test <==> run cn.net.fasttest.FastSpringTests#test");

        System.out.println("---------------------------------------------------------------");

    }
}

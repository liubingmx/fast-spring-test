package cn.net.fasttest.command.handler;

import cn.net.fasttest.command.CommandHandler;
import cn.net.fasttest.event.EventEnum;
import cn.net.fasttest.event.FastSpringTestEvent;
import cn.net.fasttest.event.FastSpringTestListener;
import cn.net.fasttest.command.Command;
import cn.net.fasttest.command.CommandHandlerFactory;
import cn.net.fasttest.event.Subscribe;
import cn.net.fasttest.exception.FastTestException;

/**
 * @author bing
 * @create 2024/01/14
 */
public class ReRunCommandHandler implements CommandHandler, FastSpringTestListener {

    Command lasRunCommand;

    @Override
    public Command getCommand() {
        return new Command("rerun", "Rerun last test case");
    }

    @Override
    public void run(Command command) {
        if (lasRunCommand == null) {
            throw new FastTestException("Please execute the run command first ");
        }
        CommandHandlerFactory.getCommandHandler(lasRunCommand.getName()).run(lasRunCommand);
    }

    @Override
    @Subscribe(EventEnum.EXECUTE_COMMAND)
    public void listen(FastSpringTestEvent event) {
        if (!(event.getSource() instanceof Command)) {
            return;
        }
        Command lastCommand = (Command)event.getSource();
        if (!lastCommand.getName().equalsIgnoreCase(new RunCommandHandler().getCommand().getName())) {
            return;
        }
        lasRunCommand = lastCommand;
    }
}

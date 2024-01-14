package com.fast.spring.test.command.handler;

import com.fast.spring.test.command.Command;
import com.fast.spring.test.command.CommandHandler;
import com.fast.spring.test.command.CommandHandlerFactory;
import com.fast.spring.test.event.EventEnum;
import com.fast.spring.test.event.FastSpringTestEvent;
import com.fast.spring.test.event.FastSpringTestListener;
import com.fast.spring.test.runner.TestRunResult;

/**
 * @author bing
 * @create 2024/01/14
 */
public class ReRunCommandHandler implements CommandHandler, FastSpringTestListener {

    Command lasRunCommand;

    @Override
    public Command getCommand() {
        return new Command("rerun");
    }

    @Override
    public void run(Command command) {
        CommandHandlerFactory.getCommandHandler(lasRunCommand.getName()).run(lasRunCommand);
    }

    @Override
    public void listen(FastSpringTestEvent event) {
        if (!EventEnum.EXECUTE_COMMAND.equals(event.getEvent())) {
            return;
        }
        if (!(event.getSource() instanceof Command)) {
            return;
        }
        Command lastCommand = (Command)event.getSource();
        if (!lastCommand.getName().equalsIgnoreCase("RUN")) {
            return;
        }
        lasRunCommand = lastCommand;
    }
}

package com.fast.spring.test.command.handler;

import com.fast.spring.test.command.Command;
import com.fast.spring.test.command.CommandHandler;
import com.fast.spring.test.loader.DefaultHotClassLoader;
import com.fast.spring.test.loader.HotLoadClassLoader;
import com.fast.spring.test.runner.RunnerFactory;

import java.util.Optional;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class RunCommandHandler implements CommandHandler {

    private HotLoadClassLoader hotLoadClassLoader = new DefaultHotClassLoader();

    public RunCommandHandler() {
    }

    @Override
    public Command getCommand() {
        return new Command("run", null, null);
    }

    @Override
    public void run(Command command) {
        Optional<Command.Option> first = command.getOptions().stream().findFirst();
        String args = first.get().getArgs();
        String[] classNameAndMethodName = args.split("#");
        Class<?> clazz = hotLoadClassLoader.loadTestClass(classNameAndMethodName[0]);
        String methodName = classNameAndMethodName.length > 1 ? classNameAndMethodName[1] : null;
        RunnerFactory.getRunner(methodName, clazz).run(clazz, methodName);
    }
}

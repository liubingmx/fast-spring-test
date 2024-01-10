package com.fast.spring.test.command;

import com.fast.spring.test.loader.HotLoadClassLoader;
import com.fast.spring.test.runner.RunnerFactory;

import java.util.Optional;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class RunCommandHandler implements CommandHandler{

    private HotLoadClassLoader hotLoadClassLoader;

    public RunCommandHandler(HotLoadClassLoader hotLoadClassLoader) {
        this.hotLoadClassLoader = hotLoadClassLoader;
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
        RunnerFactory.getRunner(classNameAndMethodName[1], clazz).run(clazz, classNameAndMethodName[1]);
    }
}

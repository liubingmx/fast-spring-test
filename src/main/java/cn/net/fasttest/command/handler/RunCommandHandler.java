package cn.net.fasttest.command.handler;

import cn.net.fasttest.command.CommandHandler;
import cn.net.fasttest.loader.HotLoadClassLoader;
import cn.net.fasttest.runner.RunnerFactory;
import cn.net.fasttest.command.Command;
import cn.net.fasttest.loader.DefaultHotClassLoader;

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

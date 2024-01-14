package com.fast.spring.test.command.handler;

import com.fast.spring.test.command.Command;
import com.fast.spring.test.command.CommandHandler;
import com.fast.spring.test.event.EventEnum;
import com.fast.spring.test.event.FastSpringTestEvent;
import com.fast.spring.test.event.FastSpringTestListener;
import com.fast.spring.test.runner.TestRunResult;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class ShowCommandHandler implements CommandHandler, FastSpringTestListener {

    TestRunResult lastRunResult;

    @Override
    public Command getCommand() {
        return new Command("show");
    }

    @Override
    public void run(Command command) {
        showResult();
    }

    private void log(String content, Object... args) {
        System.out.println(String.format(content, args));
    }

    @Override
    public void listen(FastSpringTestEvent event) {
        if (!EventEnum.RUN_TESTCASE.equals(event.getEvent())) {
            return;
        }
        if (!(event.getSource() instanceof TestRunResult)) {
            return;
        }
        lastRunResult = (TestRunResult)event.getSource();
        showResult();
    }

    private void showResult() {
        if (TestRunResult.state.SUCCESSFUL.name().equals(lastRunResult.getState())) {
            log("------------------------execution finished : %s" + "------------------------", lastRunResult.getDisplayName());
        } else {
            log("------------------------execution failed : %s" + "------------------------", lastRunResult.getDisplayName());
        }
    }
}

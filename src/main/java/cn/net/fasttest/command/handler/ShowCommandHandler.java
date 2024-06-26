package cn.net.fasttest.command.handler;

import cn.net.fasttest.command.Command;
import cn.net.fasttest.command.CommandHandler;
import cn.net.fasttest.event.EventEnum;
import cn.net.fasttest.event.FastSpringTestEvent;
import cn.net.fasttest.event.FastSpringTestListener;
import cn.net.fasttest.event.Subscribe;
import cn.net.fasttest.runner.TestRunResult;
import cn.net.fasttest.utils.FontColorUtil;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class ShowCommandHandler implements CommandHandler, FastSpringTestListener {

    TestRunResult lastRunResult;

    @Override
    public Command getCommand() {
        return new Command("show", "Show the results of the last run");
    }

    @Override
    public void run(Command command) {
        showResult();
    }

    private void log(String content, Object... args) {
        System.out.println(String.format(content, args));
    }

    @Override
    @Subscribe(EventEnum.RUN_TESTCASE)
    public void listen(FastSpringTestEvent event) {
        if (!(event.getSource() instanceof TestRunResult)) {
            return;
        }
        lastRunResult = (TestRunResult)event.getSource();
        showResult();
    }

    private void showResult() {
        if (TestRunResult.state.SUCCESSFUL.name().equals(lastRunResult.getState())) {
            log("------------------------execution finished : %s" + "------------------------", FontColorUtil.format("√ " + lastRunResult.getDisplayName(), FontColorUtil.BULE));
        } else {
            log("------------------------execution failed : %s" + "------------------------", FontColorUtil.format("× " + lastRunResult.getDisplayName(), FontColorUtil.RED));
            System.out.println();
            if (lastRunResult.getThrowable() != null) {
                lastRunResult.getThrowable().printStackTrace(System.out);
            }
        }
        System.out.println();
        if (lastRunResult.getTotalTestCount() != lastRunResult.getStarted()) {
            return;
        }
        log("--------------------------------- Summary ---------------------------------");
        log("RUN %s", lastRunResult.getUniqueId());
        log("Result -- started:%d -- succeeded: %d -- failed: %d -- skipped: %d -- cost: %d ms",
                lastRunResult.getStarted(), lastRunResult.getSucceeded(), lastRunResult.getFailed(),
                lastRunResult.getSkipped(), lastRunResult.getCost());

        log("You can use \"SHOW\" command to see detail.");
        log("You can use \"RERUN\" command to rerun these cases.");
    }
}

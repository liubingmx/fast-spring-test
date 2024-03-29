package cn.net.fasttest.runner.junit;

import cn.net.fasttest.event.EventBus;
import cn.net.fasttest.event.EventEnum;
import cn.net.fasttest.event.FastSpringTestEvent;
import cn.net.fasttest.runner.TestRunResult;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class JunitTestExecutionListener extends SummaryGeneratingListener implements TestExecutionListener {

    private Class<?> clazz;

    private String method;

    public JunitTestExecutionListener(Class<?> clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        super.executionFinished(testIdentifier, testExecutionResult);
        if (!testIdentifier.isTest()) {
            return;
        }
        TestExecutionSummary summary = getSummary();
        Optional<Throwable> throwable = testExecutionResult.getThrowable();
        TestRunResult testRunResult = new TestRunResult();
        testRunResult.setState(testExecutionResult.getStatus().name());
        testRunResult.setDisplayName(testIdentifier.getDisplayName());
        testRunResult.setUniqueId(method != null ? clazz.getName() + "#" + method : clazz.getName());
        testRunResult.setThrowable(throwable.orElse(null));
        testRunResult.setCost(System.currentTimeMillis() - summary.getTimeStarted());
        testRunResult.setFailed(summary.getTestsFailedCount());
        testRunResult.setSucceeded(summary.getTestsSucceededCount());
        testRunResult.setSkipped(summary.getTestsSkippedCount());
        testRunResult.setStarted(summary.getTestsStartedCount());
        testRunResult.setTotalTestCount(summary.getTestsFoundCount());
        EventBus.publishEvent(new FastSpringTestEvent(EventEnum.RUN_TESTCASE, testRunResult));
    }
}

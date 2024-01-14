package com.fast.spring.test.runner.junit;

import com.fast.spring.test.event.EventBus;
import com.fast.spring.test.event.EventEnum;
import com.fast.spring.test.event.FastSpringTestEvent;
import com.fast.spring.test.runner.TestRunResult;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.List;
import java.util.Optional;

/**
 * @author liubingmx@163.com
 * @create 2024/01/12
 */
public class JunitTestExecutionListener extends SummaryGeneratingListener implements TestExecutionListener {

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (!testIdentifier.isTest()) {
            return;
        }
        TestExecutionSummary summary = getSummary();
        Optional<Throwable> throwable = testExecutionResult.getThrowable();
        TestRunResult testRunResult = new TestRunResult();
        testRunResult.setUniqueId(testIdentifier.getUniqueId());
        testRunResult.setState(testExecutionResult.getStatus().name());
        testRunResult.setDisplayName(testIdentifier.getDisplayName());
        testRunResult.setThrowable(throwable.orElse(null));
        EventBus.publishEvent(new FastSpringTestEvent(EventEnum.RUN_TESTCASE, testRunResult));
    }
}

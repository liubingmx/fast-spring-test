package cn.net.fasttest.runner.testng;

import cn.net.fasttest.event.EventBus;
import cn.net.fasttest.event.EventEnum;
import cn.net.fasttest.event.FastSpringTestEvent;
import cn.net.fasttest.runner.TestRunResult;
import org.junit.platform.engine.TestExecutionResult;
import org.testng.*;

import java.util.Map;
import java.util.Set;

/**
 * @author bing
 * @create 2024/01/21
 */
public class TestNGListener implements ISuiteListener {

    private Class<?> clazz;

    private String method;

    public TestNGListener(Class<?> clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }

    @Override
    public void onFinish(ISuite suite) {
        Map<String, ISuiteResult> results = suite.getResults();
        ISuiteResult iSuiteResult = suite.getResults().get(this.method);
        ITestContext testContext = iSuiteResult.getTestContext();
        IResultMap failedTests = testContext.getFailedTests();
        Set<ITestResult> allResults = failedTests.getAllResults();
        Throwable throwable = null;
        for (ITestResult allResult : allResults) {
            throwable = allResult.getThrowable();
        }

        TestRunResult testRunResult = new TestRunResult();
        testRunResult.setState(testContext.getFailedTests().size() != 0 ? TestExecutionResult.Status.FAILED.name() : TestExecutionResult.Status.SUCCESSFUL.name());

        testRunResult.setDisplayName(method);
        testRunResult.setUniqueId(clazz.getName() + "#" + method);
        testRunResult.setThrowable(throwable);
        testRunResult.setCost(testContext.getEndDate().getTime() - testContext.getStartDate().getTime());
        testRunResult.setFailed(testContext.getFailedTests().size());
        testRunResult.setSucceeded(testContext.getPassedTests().size());
        testRunResult.setSkipped(testContext.getSkippedTests().size());
        testRunResult.setStarted(results.size());
        EventBus.publishEvent(new FastSpringTestEvent(EventEnum.RUN_TESTCASE, testRunResult));
    }
}

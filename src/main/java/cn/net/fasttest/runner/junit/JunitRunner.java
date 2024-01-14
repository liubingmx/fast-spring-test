package cn.net.fasttest.runner.junit;

import cn.net.fasttest.runner.TestRunner;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;

/**
 * @author liubingmx@163.com
 * @create 2024/01/07
 */
public class JunitRunner implements TestRunner {

    @Override
    public void run(Class<?> clazz, String method) {
        Launcher launcher = LauncherFactory.create();
        LauncherDiscoveryRequest request = null;
        if (method != null) {
            request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectMethod(clazz, method))
                    .build();
        } else {
            request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectClass(clazz))
                    .build();
        }
        JunitTestExecutionListener junitTestExecutionListener = new JunitTestExecutionListener(clazz, method);
        launcher.registerTestExecutionListeners(junitTestExecutionListener);
        launcher.execute(request);
    }
}

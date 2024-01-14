package com.fast.spring.test.runner.junit;

import com.fast.spring.test.runner.TestRunner;
import org.junit.platform.engine.discovery.ClassSelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.MethodSelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

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
        launcher.registerTestExecutionListeners(new JunitTestExecutionListener());
        launcher.execute(request);
    }
}

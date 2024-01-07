package com.spring.test.fast.runner;

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
public class JunitRunner implements TestRunner{

    @Override
    public void run(Class<?> clazz, String method) {
        Launcher launcher = LauncherFactory.create();
        MethodSelector methodSelector = DiscoverySelectors.selectMethod(clazz, method);
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(methodSelector).build();
        launcher.execute(request);
    }
}

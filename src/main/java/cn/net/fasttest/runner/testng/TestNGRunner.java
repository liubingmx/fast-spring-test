package cn.net.fasttest.runner.testng;

import cn.net.fasttest.runner.TestRunner;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bing
 * @create 2024/01/21
 */
public class TestNGRunner implements TestRunner {

    @Override
    public void run(Class<?> clazz, String method) {
        XmlClass xmlClass = new XmlClass(clazz);
        // now mention the methods to be included. You may use setExcludedMethods depending on the requirement.
        XmlInclude methodIn = new XmlInclude(method);
        List<XmlInclude> list = new ArrayList<>();
        list.add(methodIn);
        xmlClass.setIncludedMethods(list);
        XmlSuite suite = new XmlSuite();
        suite.setName(method);

        XmlTest test = new XmlTest(suite);
        // internally, the test method is also added to the suite object
        test.setName(method);
        List<XmlClass> clazzList = new ArrayList<>();
        clazzList.add(xmlClass);
        test.setXmlClasses(clazzList);

        TestNG t = new TestNG();
        List<XmlSuite> suiteList = new ArrayList<>();
        suiteList.add(suite);
        t.setVerbose(0);
        t.setXmlSuites(suiteList);
        t.addListener(new TestNGListener(clazz, method));
        t.run();
    }
}

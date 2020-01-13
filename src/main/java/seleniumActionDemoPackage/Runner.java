package seleniumActionDemoPackage;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class Runner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG(false);
        List<Class<? extends ITestNGListener>> classes = new ArrayList<>();
        testNG.setListenerClasses(classes);
        testNG.setTestSuites(Collections.singletonList("src/main/resources/suites/tests.xml"));
        TestListenerAdapter results = new TestListenerAdapter();
        testNG.addListener(results);
        boolean hasFailures;
        try {
            testNG.run();
            hasFailures = results.getFailedTests().size() > 0 || results.getSkippedTests().size() > 0;
        } catch (Throwable e) {
            hasFailures = true;
            e.printStackTrace();
        }
        System.exit(hasFailures ? 1 : 0);
    }
}

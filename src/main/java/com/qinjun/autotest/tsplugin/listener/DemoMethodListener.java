package com.qinjun.autotest.tsplugin.listener;

import com.qinjun.autotest.tsplugin.annotation.DemoTestData;
import com.qinjun.autotest.tsplugin.annotation.DemoUrl;
import org.testng.*;

import java.lang.reflect.Field;

public class DemoMethodListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult testResult) {
        ITestNGMethod testNGMethod = invokedMethod.getTestMethod();
        String methodName = testNGMethod.getMethodName();
        if (methodName.equals("baseTest")) {
            ITestClass testClass = testNGMethod.getTestClass();
            Object object = testNGMethod.getInstance();
            Class cls = testClass.getRealClass();

            DemoTestData demoTestData = (DemoTestData)cls.getAnnotation(DemoTestData.class);
            DemoUrl demoUrl = (DemoUrl) cls.getAnnotation(DemoUrl.class);

            String demoTestDataStr = demoTestData.value();
            String demoUrlStr = demoUrl.value();

            try {
                Field fieldData = cls.getSuperclass().getDeclaredField("testData");
                fieldData.set(object,demoTestDataStr);

                Field fieldUrl = cls.getSuperclass().getDeclaredField("url");
                fieldUrl.set(object,demoUrlStr);
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}

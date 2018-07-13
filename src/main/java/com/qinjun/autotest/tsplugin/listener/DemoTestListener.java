package com.qinjun.autotest.tsplugin.listener;

import com.qinjun.autotest.tsplugin.annotation.DemoTestData;
import org.testng.*;

import java.util.HashMap;
import java.util.Map;

public class DemoTestListener implements ITestListener {


    @Override
    public void onStart(ITestContext testContext) {
        ITestNGMethod[] testNGMethods = testContext.getAllTestMethods();
        ITestNGMethod testNGMethod = testNGMethods[0];
        String testNGMethodMethodName = testNGMethod.getMethodName();
        if (testNGMethodMethodName.equals("baseTest")) {
            ITestClass testClass = testNGMethod.getTestClass();
            Class cls = testClass.getRealClass();
            DemoTestData demoTestData = (DemoTestData) cls.getAnnotation(DemoTestData.class);
            String demoTestDataStr = demoTestData.value();
            Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("testData",demoTestDataStr);
            testContext.getCurrentXmlTest().setParameters(paramMap);
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }



    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}

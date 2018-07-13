package com.qinjun.autotest.tsplugin.base;

import com.qinjun.autotest.tsplugin.test.EnumResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class BaseCase extends BaseSuite {
    private final static Logger logger = LoggerFactory.getLogger(BaseCase.class);
    public String url;
    public String testData;


    public BaseCase() {

    }

     @BeforeClass
    public void setup() {
    }

    @Test
    public void baseTest()  {
        EnumResult result = EnumResult.PASS;
        logger.info("==========[Start Case]==========");
        logger.info("Request data path:[{}]", testData);
        try {
            logger.info("==========[Send Request]==========");
            result = send(url, testData);
            logger.info("==========[End Send]==========");
            if (result == EnumResult.PASS) {
                logger.info("==========[Wait]==========");
                result = wait(result);
                logger.info("==========[End Wait]==========");
                if (result == EnumResult.PASS) {
                    logger.info("==========[Verify]==========");
                    result = verify(result);
                    logger.info("==========[End Verify]==========");
                }
            }
        } catch (Exception e) {
            result = EnumResult.FAIL;
            String errMsg = "Get exception when send request:" + e;
            result.setResultMsg(errMsg);
            logger.error(errMsg);
        }

        if (result == EnumResult.FAIL) {
            logger.error("==========[End Case:FAIL]==========");
            String errMsg = result.getResultMsg();
            logger.error(errMsg);
            assert false : errMsg;
        } else if (result == EnumResult.SKIP) {
            logger.warn("==========[End Case:SKIP]==========");
            throw new SkipException("Skip the case");
        } else {
            logger.info("==========[End Case:PASS]==========");
        }
    }


    private EnumResult send(String url, String testDataPath) {
        EnumResult result = EnumResult.PASS;
        File testDataPathFile = new File(testDataPath);
        if (testDataPathFile.isDirectory()) {

        }


        return result;
    }


    private EnumResult wait(EnumResult requestResult) {
        EnumResult result = EnumResult.PASS;

        return result;
    }


    private EnumResult verify(EnumResult waitResult) {
        EnumResult result = EnumResult.PASS;


        return result;
    }





    @AfterClass
    public void cleanup() {
        logger.info("==========[End Case]==========");
    }
}
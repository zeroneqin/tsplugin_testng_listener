package com.qinjun.autotest.tsplugin.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseSuite {
    private final static Logger logger = LoggerFactory.getLogger(BaseSuite.class);

    @BeforeSuite
    public void beforeSuite() {
        logger.info("==========[Start Suite]==========");
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("==========[End Suite]==========");
    }
}
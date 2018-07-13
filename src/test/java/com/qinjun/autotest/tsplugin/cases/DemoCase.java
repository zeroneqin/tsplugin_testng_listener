package com.qinjun.autotest.tsplugin.cases;


import com.qinjun.autotest.tsplugin.annotation.DemoTestData;
import com.qinjun.autotest.tsplugin.annotation.DemoUrl;
import com.qinjun.autotest.tsplugin.base.BaseCase;


@DemoUrl("http://www.mocky.io/v2/test")
@DemoTestData("data/body.json")
public class DemoCase extends BaseCase {

}
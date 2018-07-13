package com.qinjun.autotest.tsplugin.test;

import java.util.List;

public enum EnumResult {
    PASS("Passed"), FAIL("Failed"),SKIP("Skipped");

    ThreadLocal<String> resultMsg;
    ThreadLocal<List<String>> additionalInfos = new ThreadLocal<List<String>>();

    EnumResult(final String resultMsg) {
        this.resultMsg = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return  resultMsg;
            }
        };
    }

    public String getResultMsg() {
        return this.resultMsg.get();
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg.set(resultMsg);
    }

    public List<String> getAdditionalInfos() {
        return additionalInfos.get();
    }

    public void setAdditionalInfos(List<String> additionalInfos) {
        this.additionalInfos.set(additionalInfos);
    }
}

package com.lolaflora.utils;

import com.lolaflora.core.PropKey;
import com.lolaflora.core.PropertyReader;
import io.qameta.allure.Step;

import java.io.IOException;

public class LogHelper {

    public static synchronized boolean logToReport(String meesage) throws IOException {

        boolean isLogEnabled = PropertyReader.getInstance()
                .getProperty(PropKey.ALLURE_REPORT_STEP_LOG
                        .getPropVal()).equalsIgnoreCase("ENABLE");

        if(isLogEnabled){
            logToAllureReport(meesage);
            return true;
        }
        return false;
    }

    @Step("{0}")
    private static synchronized void logToAllureReport(String message){

    }
}

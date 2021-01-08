package com.lolaflora.utils;

import com.lolaflora.core.PropKey;
import com.lolaflora.core.PropertyReader;
import com.lolaflora.driver.BrowserType;
import java.io.IOException;

public class SystemPropertyHelper {


    public static String getRemoteVal() throws IOException {

        String msg = "";
        String valueFromPropFile = PropertyReader.getInstance()
                .getProperty(PropKey.REMOTE.getPropVal());

        String remoteVal = System.getProperty(PropKey.REMOTE.getPropVal());

        if(remoteVal.length()>0){
            if(remoteVal.equalsIgnoreCase("TRUE") ||
                    remoteVal.equalsIgnoreCase("FALSE")){

                return remoteVal;
            }else{

                msg = "Incorrect system property " + remoteVal + " value specified for"
                        + PropKey.REMOTE.getPropVal() +
                        " ...defaulting to property provided by the application.properties file " +
                        PropKey.REMOTE.getPropVal() + " : " + valueFromPropFile;

                System.out.println(msg);
            }
        }

        msg =   "No system property specified" +
                " ...defaulting to application.properties file ->  " +
                PropKey.REMOTE.getPropVal() + " : " + valueFromPropFile;

        System.out.println(msg);

        return valueFromPropFile;
    }

    public static String getHubUrl() throws IOException {

        String hubUrl = System.getProperty(PropKey.HUB_URL.getPropVal());

        if(hubUrl.length()>0){
            return hubUrl;
        }

        return PropertyReader.getInstance().getProperty(PropKey.HUB_URL.getPropVal());
    }

    public static BrowserType getBrowserFromSystemVariable() throws IOException {

        String browser = System.getProperty(PropKey.BROWSER.getPropVal());

        if(browser.length()<=0){
            browser = PropertyReader.getInstance().getProperty(PropKey.BROWSER.getPropVal());
        }

        if(browser.equalsIgnoreCase("CHROME")){
            return BrowserType.CHROME;
        }else if (browser.equalsIgnoreCase("FIREFOX")){
            return BrowserType.FIREFOX;
        }
        else if(browser.equalsIgnoreCase("IE")){
            return BrowserType.IE;
        }
        else {
            return null;
        }
    }
}

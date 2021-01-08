package com.lolaflora.driver;

import com.lolaflora.core.PropKey;
import com.lolaflora.core.PropertyReader;
import com.lolaflora.utils.Helper;
import com.lolaflora.utils.SystemPropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static com.lolaflora.utils.Helper.isRemote;

public class DriverFactory {

    public static PropertyReader prop;
    public static WebDriver driver = null;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() throws IOException {
        if(driver == null) {

            if(isRemote()) {
                try {
                    driverThreadLocal.set(new RemoteWebDriver(new URL(Helper.getHubUrl()),
                            getBrowser().getBrowserCapabilities()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }else {
                driverThreadLocal.set(getBrowser().getWebDriver());
            }
        }
        driverThreadLocal.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        driverThreadLocal.get().quit();
    }

    private static BrowserType getBrowser() throws IOException {

        BrowserType browserType = SystemPropertyHelper.getBrowserFromSystemVariable();

        if(browserType!=null) {
            return browserType;
        }
        if(prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("CHROME")) {
            return BrowserType.CHROME;
        }else if(prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("FIREFOX")) {
            return BrowserType.FIREFOX;
        }else if(prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("IE")) {
            return BrowserType.IE;
        }else {
            return BrowserType.CHROME;
        }
    }
}
package com.lolaflora.driver;

import com.lolaflora.utils.Helper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import java.io.IOException;

public enum BrowserType implements DriverSetup{

    CHROME {
        @Override
        public Capabilities getBrowserCapabilities() {
            Capabilities capabilities = getBrowserOptions();
            return capabilities;
        }

        @Override
        public ChromeOptions getBrowserOptions() {

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--incognito");
            //chromeOptions.addArguments("--window-size=1920,1080");
            //chromeOptions.addArguments("--headless");
            chromeOptions.setAcceptInsecureCerts(true);

            return chromeOptions;
        }

        @Override
        public WebDriver getWebDriver() throws IOException {
            String browserPath = Helper.getBrowserPath();
            System.setProperty("webdriver.chrome.driver", browserPath);
            ChromeOptions chromeOptions = getBrowserOptions();
            return new ChromeDriver(chromeOptions);
        }
    },

    FIREFOX {
        @Override
        public Capabilities getBrowserCapabilities() {
            Capabilities capabilities = getBrowserOptions();
            return capabilities;
        }

        @Override
        public FirefoxOptions getBrowserOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            FirefoxProfile firefoxProfile = new FirefoxProfile();

            firefoxProfile.setAcceptUntrustedCertificates(true);
            firefoxProfile.setAssumeUntrustedCertificateIssuer(false);

            firefoxOptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

            return firefoxOptions;
        }
        @Override
        public WebDriver getWebDriver() throws IOException {
            String browserPath = Helper.getBrowserPath();
            System.setProperty("webdriver.gecko.driver", browserPath);
            FirefoxOptions firefoxOptions = getBrowserOptions();
            return new FirefoxDriver(firefoxOptions);
        }
    },

    IE {
        @Override
        public Capabilities getBrowserCapabilities() {

            Capabilities capabilities = getBrowserOptions();
            return capabilities;
        }

        @Override
        public InternetExplorerOptions getBrowserOptions() {
            InternetExplorerOptions capabilities= new InternetExplorerOptions();
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,false);
            capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
            capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);
            return capabilities;

        }

        @Override
        public WebDriver getWebDriver() throws IOException {

            String browserPath = Helper.getBrowserPath();
            System.setProperty("webdriver.ie.driver",
                    browserPath);
            InternetExplorerOptions internetExplorerOptions = getBrowserOptions();
            return new InternetExplorerDriver(internetExplorerOptions);
        }
    };
}

package com.lolaflora.driver;

import com.lolaflora.steps.TestListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(TestListener.class)
public class DriverManager extends DriverFactory{

    @BeforeAll
    public static void setUp() throws IOException {
        driver = getDriver();
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        driver=null;
        quitDriver();
    }
}
package com.lolaflora.steps;

import com.lolaflora.driver.DriverManager;
import com.lolaflora.utils.Helper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import java.io.IOException;
import java.util.Optional;

public class TestListener extends DriverManager implements TestWatcher {

    private final Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        logger.info("Test has been successful");
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable ) {
        logger.error("Test case has failed");
    }
}
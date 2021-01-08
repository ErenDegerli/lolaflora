package com.lolaflora.steps;

import com.lolaflora.driver.DriverFactory;
import com.lolaflora.pages.DashBoardPage;
import com.lolaflora.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Login")
@DisplayName("lolaflora.com Login Test")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(TestListener.class)
public class CicekSepeti_Test {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final DashBoardPage dashBoardPage;
    private final Logger logger;
    private final String emailValidationMessage;
    private final String passwordValidationMessage;
    private final String emptyFieldValidationMessage;
    private final String loginPageHeading;
    private final String myAccount;
    private final String popupMessage;

    public CicekSepeti_Test() throws IOException {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        dashBoardPage = new DashBoardPage(driver);
        logger = Logger.getLogger(CicekSepeti_Test.class);

        //I stored text here in order to provide to see assertions result in detail and also not to hard code them in test methods for multiple use
        emailValidationMessage = "Please enter a valid e-mail address.";
        passwordValidationMessage = "Please enter minimum 3 and maximum 20 characters.";
        emptyFieldValidationMessage = "Required field.";
        loginPageHeading = "Sign In";
        myAccount = "My Account";
        popupMessage = "E-mail address or password is incorrect. Please check your information and try again.";
    }

    @Test
    @DisplayName("Sign In Heading Validation")
    public void loginPageHeadingValidation() throws IOException {
        logger.info("Opening the login page and see 'Sign In' on the page");
        loginPage.navigateToLoginPage();
        assertEquals(loginPageHeading, loginPage.getHeadingOfLoginPage());
    }

    @Test
    @DisplayName("Login Happy Path")
    public void happyPath() throws IOException {
        logger.info("Fill the login form with valid credentials and see 'My Account' on the Dashboard");
        loginPage.loginWithValidCredentials();
        assertEquals(myAccount, dashBoardPage.getMenuTitle());
    }

    @Test
    @DisplayName("Fill with Invalid Email")
    public void invalidEmail() throws IOException {
        logger.info("Fill the login form with invalid email and see error message");
        assertEquals(emailValidationMessage,loginPage.loginWithInvalidEmailAndGetValidationMessage());
    }

    @Test
    @DisplayName("Fill with Invalid Password")
    public void invalidPassword() throws IOException {
        logger.info("Fill the login form with invalid password and see error message");
        assertEquals(passwordValidationMessage,loginPage.loginWithInvalidPasswordAndGetPopupMessage());
    }

    @Test
    @DisplayName("Fill with Wrong Password")
    public void wrongPassword() throws IOException {
        logger.info("Fill the login form with wrong password and see popup message");
        assertEquals(popupMessage,loginPage.loginWithWrongPasswordAndGetPopupMessage());
    }

    @Test
    @DisplayName("Blank Fields Separately")
    public void separateBlankFields() throws IOException {
        logger.info("Fill the login form with blank email and password separately and see error message");
        assertAll(
                () -> assertEquals(emptyFieldValidationMessage, loginPage.loginWithBlankEmailAndGetValidationMessage()),
                () -> assertEquals(emptyFieldValidationMessage, loginPage.loginWithBlankPasswordAndGetValidationMessage())
        );
    }

    @Test
    @DisplayName("Fully Blank Fields")
    public void fullBlank() throws IOException {
        logger.info("Click on Sign In button without filling the form and see error messages");
        assertEquals(emptyFieldValidationMessage,loginPage.getBlankFormValidationMessage());
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
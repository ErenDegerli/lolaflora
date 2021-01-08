package com.lolaflora.pages;

import com.lolaflora.core.Base;
import com.lolaflora.core.PropKey;
import com.lolaflora.core.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;

public class LoginPage extends Base {

    @FindBy(className = "login__heading")
    private WebElement heading;

    @FindBy(id = "EmailLogin")
    private WebElement emailInputField;

    @FindBy(id = "Password")
    private WebElement passwordInputField;

    @FindBy(css = "#userLogin > div:nth-child(6) > button")
    private WebElement signInButton;

    @FindBy(id = "EmailLogin-error")
    private WebElement emailValidationMessage;

    @FindBy(id = "Password-error")
    private WebElement passwordValidationMessage;

    @FindBy(className = "modal-body")
    private WebElement popupMessage;

    private final String validEmail;
    private final String validPassword;
    private final String invalidEmail;
    private final String invalidPassword;
    private final String wrongPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
        validEmail = "venosa6896@yutongdt.com";
        validPassword = "Test1234";
        invalidEmail = "a)@gmail.com";
        invalidPassword = "123456789123456789123";
        wrongPassword = "Test12345";
    }

    public void navigateToLoginPage() throws IOException {
        driver.get(PropertyReader.getInstance().getProperty(PropKey.URL.getPropVal()));
    }

    public String getHeadingOfLoginPage() {
        return waitUntilVisible(heading).getText();
    }

    public void loginWithValidCredentials() throws IOException {
        navigateToLoginPage();
        waitUntilClickable(emailInputField).sendKeys(validEmail);
        passwordInputField.sendKeys(validPassword);
        signInButton.click();
    }

    public String loginWithInvalidEmailAndGetValidationMessage() throws IOException {
        navigateToLoginPage();
        waitUntilClickable(emailInputField).sendKeys(invalidEmail);
        passwordInputField.sendKeys(validPassword);
        signInButton.click();
        return waitUntilVisible(emailValidationMessage).getText();
    }

    public String loginWithInvalidPasswordAndGetPopupMessage() throws IOException {
        navigateToLoginPage();
        waitUntilClickable(emailInputField).sendKeys(validEmail);
        passwordInputField.sendKeys(invalidPassword);
        signInButton.click();
        return waitUntilVisible(passwordValidationMessage).getText();
    }

    public String loginWithWrongPasswordAndGetPopupMessage() throws IOException {
        navigateToLoginPage();
        waitUntilClickable(emailInputField).sendKeys(validEmail);
        passwordInputField.sendKeys(wrongPassword);
        signInButton.click();
        return waitUntilVisible(popupMessage).getText();
    }

    public String loginWithBlankEmailAndGetValidationMessage() throws IOException {
        navigateToLoginPage();
        passwordInputField.sendKeys(validPassword);
        signInButton.click();
        return waitUntilVisible(emailValidationMessage).getText();
    }

    public String loginWithBlankPasswordAndGetValidationMessage() throws IOException {
        navigateToLoginPage();
        waitUntilClickable(emailInputField).sendKeys(validEmail);
        signInButton.click();
        return waitUntilVisible(passwordValidationMessage).getText();
    }

    public String getBlankFormValidationMessage() throws IOException {
        navigateToLoginPage();
        signInButton.click();
        return waitUntilVisible(passwordValidationMessage).getText();
    }
}
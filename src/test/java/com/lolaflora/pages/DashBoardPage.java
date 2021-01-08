package com.lolaflora.pages;

import com.lolaflora.core.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardPage extends Base {

    @FindBy(css = "li.user-menu__item.user-menu__item--account > a > span.user-menu__title")
    private WebElement menuTitle;

    @FindBy(css = "div.subheader-close.js-subheader-close.is-hidden > a > span")
    private WebElement closeBtn;

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public String getMenuTitle() {
        waitUntilClickable(closeBtn).click();
        return waitUntilVisible(menuTitle).getText();
    }
}
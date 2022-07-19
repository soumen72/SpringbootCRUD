package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestingLogin {


    @FindBy(id="Username-input")
    private WebElement usernameinput;

    @FindBy(id="Password-input")
    private WebElement passwordinput;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    public TestingLogin(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void Submit_login(String username, String password) {
        this.usernameinput.sendKeys(username);
        this.passwordinput.sendKeys(password);
        this.submitButton.click();
    }
}

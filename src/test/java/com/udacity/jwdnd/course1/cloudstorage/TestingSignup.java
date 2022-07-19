package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestingSignup {

//    @FindBy(id = "in-Login-Link")
//    private WebElement toLoginLink;



    @FindBy(id = "input-first-Name")
    private WebElement firstnameinput;

    @FindBy(id = "input-last-Name")
    private WebElement lastnameinput;

    @FindBy(id = "input-username")
    private WebElement usernameinput;

    @FindBy(id = "input-password")
    private WebElement passwordinput;

    //changes  goToLoginLink
    @FindBy(id = "submit-button")
    private WebElement submitbtn;

    @FindBy(id = "success-msg")
    private WebElement successMsg;


    private final JavascriptExecutor js;

    public TestingSignup(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public void signup(String firstName, String lastName, String username, String password) {

        this.firstnameinput.sendKeys(firstName);
        this.lastnameinput.sendKeys(lastName);
        this.usernameinput.sendKeys(username);
        this.passwordinput.sendKeys(password);
        this.submitbtn.click();
    }

    public String getSuccessmsg() {
        return successMsg.getText();
    }



}

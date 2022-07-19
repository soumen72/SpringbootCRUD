package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestingResult {

    //successMessageText  goToHomeLinkFromSuccess  goToHomeLinkFromError
    @FindBy(id = "success-msg-Text")
    private WebElement success_msg;

    @FindBy(id = "home-Link-SuccessFrom")
    private WebElement homeLink_Success_From;

    @FindBy(id = "home-Link-ErrorFrom")
    private WebElement homeLink_Error_From;

    private final JavascriptExecutor js;

    public TestingResult(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public String popping_SuccessMessage() {
        return success_msg.getText();
    }

    public void Error_goRouteToHomeLinkFrom() {
        //js.executeScript("arguments[0].click();", homeLink_Success_From);
        js.executeScript("arguments[0].click();", homeLink_Error_From);
    }

    public void Success_goRouteToHomeLinkFrom() {
        js.executeScript("arguments[0].click();", homeLink_Success_From);
    }



}

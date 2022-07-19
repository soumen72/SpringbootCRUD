package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestingHome {


    @FindBy(id = "logout-button")
    private WebElement logout;


    //notes
    @FindBy(id = "nav-notes-tab")
    private WebElement notes_tab;

    @FindBy(id = "note-title")
    private WebElement note_title;

    @FindBy(id = "note-description")
    private WebElement note_description;

    @FindBy(id = "note-title-on-list")
    //noteTitleOnList
    private WebElement note_Title_on_List;

    //noteDescriptionOnList
    @FindBy(id = "note-description-on-list")
    private WebElement note_desciption_On_List;



    @FindBy(id = "add-note-button")
    private WebElement add_Note_Button;

    @FindBy(id = "button-submitNote")
    private WebElement submit_Note_Button;

    @FindBy(id = "edit-note-button")
    private WebElement edit_Note_Button;

    //delete_Note_Link
    @FindBy(id = "delete-note-link")
    private WebElement delete_Note_Link;


   //CREDENTIALS

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "credential-url")
    private WebElement credentialURL;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credential-key")
    private WebElement credentialKey;

    @FindBy(id = "credential-url-on-list")
    private WebElement credentialURLOnList;

    @FindBy(id = "credential-username-on-list")
    private WebElement credentialUsernameOnList;

    @FindBy(id = "credential-password-on-list")
    private WebElement credentialPasswordOnList;

    @FindBy(id = "add-new-Credential")
    private WebElement addCredentialButton;

    @FindBy(id = "submit-credential")
    private WebElement submitCredentialButton;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteCredentialLink")
    private WebElement deleteCredentialLink;




    private final JavascriptExecutor js;
    private final WebDriver driver;
    public TestingHome(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
        driver = webDriver;
    }

    public void clickLogOut() {
        logout.click();
    }



    public boolean is_Elem_Present(By by) {

        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

   //notes


    public void clickOnNoteTab() {
        js.executeScript("arguments[0].click();", notes_tab);
    }

    //changes
    public void clickOnAddNote() {
        //addNoteButton.click();
        js.executeScript("arguments[0].click();", add_Note_Button);
    }

    public void Button_clickOnEditNote() {
        js.executeScript("arguments[0].click();", edit_Note_Button);
    }

    public void Link_clickOnNoteDelete() {
        js.executeScript("arguments[0].click();", delete_Note_Link);
    }

    public void filling_The_Note(String notetitle, String notedescription) {

        js.executeScript("arguments[0].value='" + notetitle + "';", this.note_title);
        js.executeScript("arguments[0].value='" + notedescription + "';", this.note_description);

        js.executeScript("arguments[0].click();", submit_Note_Button);
    }


    public String List_get_NoteTitle() {
        return note_Title_on_List.getAttribute("innerHTML");
    }

    public String List_get_NoteDescription() {
        return note_desciption_On_List.getAttribute("innerHTML");
    }


    //Credentials

    public void Click_The_CredentialTab() {
        js.executeScript("arguments[0].click();", credentialsTab);
    }

    public void Click_Add_CredentialButton() {
        js.executeScript("arguments[0].click();", addCredentialButton);
    }

    public void click_Edit_CredentialButton() {
        js.executeScript("arguments[0].click();", editCredentialButton);
    }

    public void Link_click_Credential() {
        js.executeScript("arguments[0].click();", deleteCredentialLink);
    }

    public void Credential_filling(String credentialURL, String credentialUsername, String credentialPassword) {
        js.executeScript("arguments[0].value='" + credentialURL + "';", this.credentialURL);
        js.executeScript("arguments[0].value='" + credentialUsername + "';", this.credentialUsername);
        js.executeScript("arguments[0].value='" + credentialPassword + "';", this.credentialPassword);

        js.executeScript("arguments[0].click();", submitCredentialButton);
    }
    public String List_getCredentialUsername() {
        return credentialUsernameOnList.getAttribute("innerHTML");
    }

    public String Listof_getCredentialURL() {
        return credentialURLOnList.getAttribute("innerHTML");
    }




}

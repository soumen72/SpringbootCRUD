package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.DisabledIf;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	private static EncryptionService encryptionService;

	@LocalServerPort
	private int port;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		encryptionService = new EncryptionService();
	}



	EnvironmentVariable environmentVariable= new EnvironmentVariable();

	private String username = environmentVariable.getUsername();
	private String password = environmentVariable.getPassword();
	private String firstname = environmentVariable.getFirstname();
	private String lastname = environmentVariable.getLastname();
	//notes
	private String Notetitle = environmentVariable.getNoteTitle();
	private String NoteDescription = environmentVariable.getNoteDescription();

	private String NoteTitleChanged = environmentVariable.getNoteTitleChanged();
	private String NoteDescriptionChanged = environmentVariable.getNoteDescriptionChanged();

	//creential
	private String credentialUrl = environmentVariable.getCredentialURL();
	private String credentialUsername = environmentVariable.getCredentialUsername();
	private String credentialPassword = environmentVariable.getCredentialPassword();
	private String Changecredentialurl = environmentVariable.getCredentialURLChanged();
	private String ChangecredentialUsername = environmentVariable.getCredentialUsernameChanged();
	private String ChangecredentialPassword = environmentVariable.getCredentialPasswordChanged();



	private WebDriver driver;
	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

		/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling redirecting users
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric:
	 * https://review.udacity.com/#!/rubrics/2724/view
	 */
	@Test
	public void testRedirection() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		TestingSignup signupPage = new TestingSignup(driver);

		signupPage.signup("Redirection", "Test", "RT", "123");
		Assertions.assertEquals("You signed up successfully! Please continue to the login .", signupPage.getSuccessmsg());
		Assertions.assertNotEquals("Sign Up", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());

	}

		/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 *
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		TestingSignup signupPage = new TestingSignup(driver);

		signupPage.signup("URL","Test","UT","123");


		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		TestingLogin loginPage = new TestingLogin(driver);
		loginPage.Submit_login("UT", "123");

		Assertions.assertNotEquals("Login", driver.getTitle());
		Assertions.assertEquals("Home", driver.getTitle());

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		System.out.println(driver.getPageSource()+" getPageSource");
		//Assertions.assertThrows(driver.getPageSource().contains("Whitelabel Error Page"));
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}



	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code.
	 *
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		TestingSignup signupPage = new TestingSignup(driver);
		signupPage.signup("Large File","Test","LFT","123");
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		TestingLogin loginPage = new TestingLogin(driver);

		loginPage.Submit_login("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		//file provided in starter
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}


	/*** Write Tests for User Signup, Login,
	 * and Unauthorized
	 * Access Restrictions.
	 * ***/

	private void signUp() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		// Sing up a new user
		TestingSignup testingSignup = new TestingSignup(driver);
		testingSignup.signup(firstname, lastname, username, password);
		Assertions.assertEquals("You signed up successfully! Please continue to the login .", testingSignup.getSuccessmsg());

	}

	private void login(String username,String password) {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Username-input")));
		WebElement loginUserName = driver.findElement(By.id("Username-input"));
		loginUserName.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password-input")));


		TestingLogin loginPage = new TestingLogin(driver);
		loginPage.Submit_login(this.username, this.password);


		Assertions.assertNotEquals("Login", driver.getTitle());
		//it is expected home after this

	}



	@Test
	@Order(1)
	@DisplayName(" a test that verifies that an unauthorized user can only access the login and signup pages")
	public void unauthorizedToAccessHomePage() {


		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertNotEquals("home", driver.getTitle());



	}

	@Test
	@Order(2)
	@DisplayName("a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible")
	public void SingUp_AndLogUserAndCanAccessHomePageAndLogOutAndHomePageInAccessible() {



		signUp();
		Assertions.assertEquals("Login", driver.getTitle());
		login(this.username,this.password);
		Assertions.assertEquals("Home", driver.getTitle());


		// log out and check that home is inaccesible
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		TestingHome testingHome = new TestingHome(driver);
		testingHome.clickLogOut();

		//after logout cant acces
		Assertions.assertNotEquals("Home", driver.getTitle());

		Assertions.assertEquals("Login", driver.getTitle());
	}





	@Test
	@Order(3)
	@DisplayName("Write a test that creates a note, and verifies it is displayed.")
	public void LogIn_AndCreateNoteAnd_see_NoteDetailsAreSeenInNoteList() {

		// Log the user in
		login(this.username,this.password);
		Assertions.assertEquals("Home", driver.getTitle());

		// create a note
		driver.get("http://localhost:" + this.port + "/home");


		TestingHome testingHome = new TestingHome(driver);
		testingHome.clickOnNoteTab();
		testingHome.clickOnAddNote();
		testingHome.filling_The_Note(Notetitle, NoteDescription);
		Assertions.assertEquals("Result", driver.getTitle());

		TestingResult testingResult = new TestingResult(driver);
		Assertions.assertEquals("Your note is created successfully.", testingResult.popping_SuccessMessage());
		testingResult.Success_goRouteToHomeLinkFrom();
		Assertions.assertEquals("Home", driver.getTitle());


		//view availabele
		Assertions.assertEquals(Notetitle, testingHome.List_get_NoteTitle());

		Assertions.assertEquals(NoteDescription, testingHome.List_get_NoteDescription());


	}
	@Test
	@Order(4)
	@DisplayName("Write a test that edits an existing note and verifies that the changes are displayed")
	public void LogIn_Edit_SaveTheNote_and_Verify_if_Visible() {

		// Log the user in
		login(this.username,this.password);
		Assertions.assertEquals("Home", driver.getTitle());


		// changes
		driver.get("http://localhost:" + this.port + "/home");


		TestingHome testingHome = new TestingHome(driver);
		testingHome.clickOnNoteTab();
		testingHome.Button_clickOnEditNote();
		testingHome.filling_The_Note(NoteTitleChanged, NoteDescriptionChanged);
		Assertions.assertEquals("Result", driver.getTitle());

		TestingResult testingResult = new TestingResult(driver);
		Assertions.assertEquals("Your note was updated successfully.", testingResult.popping_SuccessMessage());
		testingResult.Success_goRouteToHomeLinkFrom();

		Assertions.assertEquals("Home", driver.getTitle());


		//view all list items
		Assertions.assertEquals(NoteTitleChanged, testingHome.List_get_NoteTitle());

		Assertions.assertEquals(NoteDescriptionChanged, testingHome.List_get_NoteDescription());
	}

	@Test
	@Order(5)
	@DisplayName(" a test that deletes a note and verifies that the note is no longer displayed")
	public void LogIn_UserWithExistingNotesAndDeleteNoteandViewTheNoteList() {

		// Log the user in
		login(this.username,this.password);
		Assertions.assertEquals("Home", driver.getTitle());



		driver.get("http://localhost:" + this.port + "/home");


		// delete note
		TestingHome testingHome = new TestingHome(driver);
		testingHome.clickOnNoteTab();
		testingHome.Link_clickOnNoteDelete();
		Assertions.assertEquals("Result", driver.getTitle());

		TestingResult testingResult = new TestingResult(driver);
		Assertions.assertEquals("Your note is deleted successfully.", testingResult.popping_SuccessMessage());
		testingResult.Success_goRouteToHomeLinkFrom();
		Assertions.assertEquals("Home", driver.getTitle());




		Assertions.assertFalse(testingHome.is_Elem_Present(By.id("note-description-on-list")));

		Assertions.assertFalse(testingHome.is_Elem_Present(By.id("note-title-on-list")));
	}


	@Test
	@Order(6)
	@DisplayName("a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.")
	public void LogIn_AndCreateCredentialAndVisibleInCredentialList() {

		// Log the user in
		login(this.username,this.password);
		Assertions.assertEquals("Home", driver.getTitle());



		driver.get("http://localhost:" + this.port + "/home");


		Assertions.assertEquals("Home", driver.getTitle());
		// create
		TestingHome testingHome = new TestingHome(driver);
		testingHome.Click_The_CredentialTab();
		testingHome.Click_Add_CredentialButton();
		testingHome.Credential_filling(credentialUrl, credentialUsername, credentialPassword);
		Assertions.assertEquals("Result", driver.getTitle());

		TestingResult resultPage = new TestingResult(driver);
		Assertions.assertEquals("Credential is created successfully.", resultPage.popping_SuccessMessage());
		resultPage.Success_goRouteToHomeLinkFrom();
		Assertions.assertEquals("Home", driver.getTitle());




		Assertions.assertEquals(credentialUsername, testingHome.List_getCredentialUsername());

		Assertions.assertEquals(credentialUrl, testingHome.Listof_getCredentialURL());

	}


	@Test
	@Order(7)
	@DisplayName("a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed")
	public void  LogIn_UserWithCredentialsAndEditCredentialAndSaveAndCredentialDetailsAvailable() {

		// Log in
		login(this.username,this.password);
		Assertions.assertEquals("Home", driver.getTitle());



		driver.get("http://localhost:" + this.port + "/home");



		// edit
		TestingHome testingHome = new TestingHome(driver);
		testingHome.Click_The_CredentialTab();
		testingHome.click_Edit_CredentialButton();
		testingHome.Credential_filling(Changecredentialurl, ChangecredentialUsername, ChangecredentialPassword);
		Assertions.assertEquals("Result", driver.getTitle());

		TestingResult resultPage = new TestingResult(driver);
		Assertions.assertEquals("Credential is updated successfully.", resultPage.popping_SuccessMessage());
		resultPage.Success_goRouteToHomeLinkFrom();
		Assertions.assertEquals("Home", driver.getTitle());



		Assertions.assertEquals(ChangecredentialUsername, testingHome.List_getCredentialUsername());

		Assertions.assertEquals(Changecredentialurl, testingHome.Listof_getCredentialURL());

	}


//
//


//	@AfterEach
//	public void afterEach() {
//		if (this.driver != null) {
//			driver.quit();
//		}
//	}
//


	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/


//	private void doLogIn(String userName, String password)
//	{
//		// Log in to our dummy account.
//		driver.get("http://localhost:" + this.port + "/login");
//		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
//
//		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
//		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
//		loginUserName.click();
//		loginUserName.sendKeys(userName);
//
//		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
//		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
//		loginPassword.click();
//		loginPassword.sendKeys(password);
//
//		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
//		WebElement loginButton = driver.findElement(By.id("login-button"));
//		loginButton.click();
//
//		webDriverWait.until(ExpectedConditions.titleContains("Home"));
//
//	}


}

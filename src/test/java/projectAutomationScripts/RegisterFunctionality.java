package projectAutomationScripts;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtilities.EssentialFunctions;
import pagesOfQaFox.AccountSuccessPage;
import pagesOfQaFox.LandingPage;
import pagesOfQaFox.RegisterAccountPage;

public class RegisterFunctionality {
	WebDriver driver;
	Actions act;
	RegisterAccountPage regacct;
	AccountSuccessPage actsucpg;
	LandingPage landpage;

	@BeforeMethod
	public void registerPageNavigation() throws IOException {
		if(EssentialFunctions.PropertyReaderForQAfox("browserName").equals("chrome"))
		driver = new ChromeDriver();
		else if(EssentialFunctions.PropertyReaderForQAfox("browserName").equals("firefox"))
			driver=new FirefoxDriver();
		else if(EssentialFunctions.PropertyReaderForQAfox("browserName").equals("edge"))
			driver=new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(EssentialFunctions.PropertyReaderForQAfox("testUrl"));
		landpage=new LandingPage(driver);
		landpage.acctMenuClick();
		landpage.regOptionSelect();
		regacct=new RegisterAccountPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	/* This test case registers user */
	@Test(priority = 1)
	public void verifyRegistrationByMandatory() throws IOException {

		regacct.enterInputFirstNameBox(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		regacct.enterInputLastNameBox(EssentialFunctions.PropertyReaderForQAfox("lastName"));		
		regacct.enterInputEmailBox(EssentialFunctions.generateEmail());
		regacct.enterInputTelephoneBox(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		regacct.enterInputValidPswdBox(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		regacct.enterInputConfirmPswdBox(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		regacct.enterNewsLetterBox(EssentialFunctions.PropertyReaderForQAfox("inputAsYes"));
		regacct.enterPrivacyPolicyField(EssentialFunctions.PropertyReaderForQAfox("inputAsYes"));
		regacct.continuebuttonClickOnRegisterPage();
		actsucpg=new AccountSuccessPage(driver);
		String expectedacctsuccessstring = "Your Account Has Been Created!";
		Assert.assertEquals(actsucpg.successpageContent(),expectedacctsuccessstring);
	}
	

	/*
	 *Pending----this is similar to TC 1> This test registers an account with valid details and goes to account success
	 * page .Then navigates to further page and verifies if its on Account page
	 */
	@Test(priority = 3)
	public void verifyRegistrationWithAllAcctDetails() throws IOException {
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword")	);
		driver.findElement(By.id("input-confirm")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();

		String successfullmessexpected = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(),
				successfullmessexpected);
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Account")).isDisplayed());

	}
	
	/*This test verifies if proper notifications are displayed when any field is not entered in register page*/
	@Test(priority=4)
	public void properNotificationForMandatoryFieldWhenFieldsNotEntered()
	{
		String expectedFirstNameMsg="First Name must be between 1 and 32 characters!";
		String expectedLastNameMsg="Last Name must be between 1 and 32 characters!";
		String expectedEmailMsg="E-Mail Address does not appear to be valid!";
		String expectedTelephoneMsg="Telephone must be between 3 and 32 characters!";
		String expectedPswdMsg="Password must be between 4 and 20 characters!";
		
		regacct.continuebuttonClickOnRegisterPage();
		Assert.assertEquals(regacct.firstNameErrorNotification(), expectedFirstNameMsg);
		Assert.assertEquals(regacct.lastNameErrorNotification(), expectedLastNameMsg);
		Assert.assertEquals(regadriver.findElement(By.xpath("//div[@class='form-group required has-error']//input[@type='email' and @name='email']/following-sibling::div")).getText(), expectedEmailMsg);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form-group required has-error']//input[@type='tel' and @name='telephone']/following-sibling::div")).getText(), expectedTelephoneMsg);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form-group required has-error']//input[@type='password' and @name='password']/following-sibling::div")).getText(), expectedPswdMsg);
		
		
	}
	

	/*This test verifies if yes newsletter option is selected as yes for newsletterfield on newsletter  page*/
	@Test(priority=5)
	public void newsletterYesOptionOnNewsletterPage() throws IOException
	{
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword")	);
		driver.findElement(By.id("input-confirm")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
		//Navigates to account success page
		driver.findElement(By.xpath("//div[@class='buttons']//a[text()='Continue']")).click();
		//navigates to My account page
		driver.findElement(By.xpath("//ul[@class='list-unstyled']/li/a[text()='Subscribe / unsubscribe to newsletter']")).click();
		//Navigates to newsletter subscription page
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-sm-10']//input[@type='radio' and @value='1']")).isSelected());
		
		
	}
	
	/*This test verifies if No newsletter option is selected as No for newsletterfield on newsletter  page*/
	@Test(priority=6)
	public void newsletterNoOptionOnNewsletterPage() throws IOException
	{
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword")	);
		driver.findElement(By.id("input-confirm")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
		//Navigates to account success page
		driver.findElement(By.xpath("//div[@class='buttons']//a[text()='Continue']")).click();
		//navigates to My account page
		driver.findElement(By.xpath("//ul[@class='list-unstyled']/li/a[text()='Subscribe / unsubscribe to newsletter']")).click();
		//Navigates to newsletter subscription page
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-sm-10']//input[@type='radio' and @value='0']")).isSelected());	
		
	}
	/*This test verifies different ways to register an account*/
	@Test(priority=7)
	public void differntWaysToRegisterUserAccount()
	{
		//My account->Register
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//li/a[text()='Register']")).isDisplayed());
		
		//My account->Login->New Customer(continue)
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary' and text()='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//li/a[text()='Register']")).isDisplayed());
		
		//My account->Login->Register(on right column)
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//li/a[text()='Register']")).isDisplayed());
	}
	
	
	/*This test verifies if password and confirm password mismatch stops user from registering account on Register page*/
	@Test(priority=8)
	public void errorOnPasswordAndConfirmPasswordMismatchOnRegPage () throws IOException
	{
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword")	);
		driver.findElement(By.id("input-confirm")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("invalidPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();	
		
	}
	

	/*
	 * This test case verifies registration of account by providing invalid emails
	 * in email field Also note how soft and hard assert changes output of test
	 */
	@Test(priority = 10)
	public void verifyRegisterAcctWithInvalidEmail() throws InterruptedException, IOException {

		// SoftAssert ast=new SoftAssert();

		String expectedmsg1 = "Please include an '@' in the email address. 'amotoori' is missing an '@'.";
		String expectedmsg2 = "Please enter a part following '@'. 'amotoori@' is incomplete.";
		// String expectedmsg2="Please Enter a part following '@'. 'amotoori@' is
		// incomplete.";
		String expectedmsg3 = "E-Mail Address does not appear to be valid!";
		String expectedmsg4 = "'.' is used at a wrong position in 'gmail.'.";

		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("invalidemailone"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// ast.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),
		// expectedmsg1);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg1);
		System.out.println("part 1 of test  passed");

		// mistake in 2
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("invalidemailtwo"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// ast.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),
		// expectedmsg2);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg2);
		System.out.println("part 2 of test  passed");

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("invalidemailthree"));
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// ast.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger' and
		// (text()='E-Mail Address does not appear to be valid!')]")).getText(),
		// expectedmsg3);
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//div[@class='text-danger' and (text()='E-Mail Address does not appear to be valid!')]"))
				.getText(), expectedmsg3);
		// System.out.println(driver.findElement(By.xpath("//input[@id='input-email'
		// ]")).getText());
		System.out.println("part 3 of test  passed");

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("invalidemailfour"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// ast.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),
		// expectedmsg4);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg4);
		System.out.println("part 4 of test  passed");

		// ast.assertAll();

	}

	/*
	 * This test case verifies registering an acct by providing invalid phone number
	 */
	@Test(priority = 11)
	public void verifyRegistringAcctByInvalidPhoneNumber() throws IOException {
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("invalidTelephone"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		Assert.assertFalse(driver.findElement(By.linkText("Success")).isDisplayed(),
				"The acct is successfully created with incorrect telephone number");
	}

	/* This test case registers user account with keyboard keys */
	@Test(priority = 12)
	public void verifyRegAcctUsingKeyboardKeys() throws InterruptedException, IOException {

		act = new Actions(driver);
//		 keyboardClicks(3);
//		 act.sendKeys(Keys.ARROW_DOWN).build().perform();
//		 driver.findElement(By.linkText("Register")).click();
		keyboardClicks(23);
		Thread.sleep(2000);
		act.sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName")).sendKeys(Keys.TAB).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName")
).sendKeys(Keys.TAB)
				.sendKeys(EssentialFunctions.generateEmail()).sendKeys(Keys.TAB).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber")).sendKeys(Keys.TAB)
				.sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword")).sendKeys(Keys.TAB).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword")).sendKeys(Keys.TAB).build().perform();
		keyboardClicks(2);
		Thread.sleep(3000);
		act.sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		keyboardClicks(21);
		Assert.assertEquals(driver.findElement(By.linkText("Success")).getText(), "Success",
				"The Success page is landed");
		// The above message gets displayed only when asserts value is not equal

	}

	public void keyboardClicks(int numofClicks) {
		for (int j = 0; j < numofClicks; j++)
			act.sendKeys(Keys.TAB);

		act.build().perform();
		System.out.println("mouse clicked");
	}

	/*
	 * This test case verifies if all fields on register account page has proper
	 * placeholder
	 */

	@Test(priority = 13)
	public void verifyAcctRegistryPageForPlaceHolders() {
		SoftAssert ast = new SoftAssert();

		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("placeholder"),
				"First Name");
		// Note xpath for placeholder First Name, value is different tag and has no
		// value next is placeholder tag with first name
		// driver.findElements(By.xpath("//input[ @ placeholder='First Name']"));

		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("placeholder"),
				"Last Name");
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='E-Mail']")).getAttribute("placeholder"),
				"E-Mail");
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Telephone']")).getAttribute("placeholder"),
				"Telephone");
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("placeholder"),
				"Password");

		ast.assertEquals(
				driver.findElement(By.xpath("//input[@placeholder='Password Confirm']")).getAttribute("placeholder"),
				"Password Confirm");
		ast.assertAll();
	}

	@Test(priority = 14)
	public void verifyRegisterAcctFieldsMarkedWithRedColor() {

		WebElement elemcss = driver.findElement(By.cssSelector("label[for='input-firstname']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String content = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", elemcss);

		System.out.println(content);
		Assert.assertEquals(content, "rgb(255, 0, 0)");

	}

	/*
	 * This test case verifies if mandatory fields on register page do not accept
	 * spaces and also it takes screenshot at the end
	 */
	@Test(priority = 16)
	public void verifyRegistryPageWithSpaces() throws InterruptedException, IOException {
		SoftAssert ast = new SoftAssert();
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("spaceKeyInputs"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("spaceKeyInputs"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("spaceKeyInputs"));
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("spaceKeyInputs"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("spaceKeyInputs"));
		driver.findElement(By.xpath("//input[@type='password' and @id='input-password']")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("spaceKeyInputs"));
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		String actualfirstnamemsg = driver
				.findElement(By.xpath("//input[@type='text' and @name='firstname']/following-sibling::div")).getText();
		String expectedfirstnamemsg = "First Name must be between 1 and 32 characters!";
		ast.assertEquals(actualfirstnamemsg, expectedfirstnamemsg);
		String actuallastnamemsg = driver
				.findElement(By.xpath("//input[@type='text' and @name='lastname']/following-sibling::div")).getText();
		String expectedlastnamemsg = "Last Name must be between 1 and 32 characters!";
		ast.assertEquals(actuallastnamemsg, expectedlastnamemsg);
		String actualemailmsg = driver
				.findElement(By.xpath("//input[@type='email' and @name='email']/following-sibling::div")).getText();
		String expectedemailmsg = "E-Mail Address does not appear to be valid!";
		ast.assertEquals(actualemailmsg, expectedemailmsg);
		ast.assertAll();
		Thread.sleep(2000);
		TakesScreenshot sct = (TakesScreenshot) driver;
		File filecpy = sct.getScreenshotAs(OutputType.FILE);
		File fp = new File("C:\\Users\\itscm\\OneDrive\\Desktop\\Live Project work\\snap.png");
		try 
		    {
			FileUtils.copyFile(filecpy, fp);
		    } 
		catch (IOException e) 
		   {
			e.printStackTrace();
		   }

	}

	/*
	 * This test case implements data driven testing concept wherein it verifies if
	 * application adheres registering acct using complex standard passwords
	 */

	@Test(priority = 17, dataProvider = "PasswordSupplyList")
	public void verifyPasswordStandardsForRegisterFunctionality(String pwd) throws IOException {
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.xpath("//input[@type='password' and @id='input-password']")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@type='password' and @id='input-confirm']")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter'and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and  @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' ]")).click();
		//String successmsgafteracctcreation="Your Account Has Been Created!";
		//Assert.assertNotEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(), successmsgafteracctcreation);		
	    Assert.assertFalse( driver.findElement(By.xpath("//div[@id='content']//h1")).isDisplayed());		
		driver.close();
	}

	@DataProvider(name = "PasswordSupplyList")
	public Object[] passwordsupplier() {
		Object[] pswd = { "12345", "abcdefghi", "abcd1234", "abcd123$", "ABCD456#" };
		return pswd;

	}
	
	/*This test case verifies if entered credentials with leading and trailing spaces in Registry page gets automatically trimmed off*/	
	@Test(priority=19)
	public void verifyRegistryPageWithLeadingAndTrailingSpaces() throws IOException
	{
	SoftAssert ast=new SoftAssert();
	//String enterName="   sm   ";
	//String lastName="   jhb   ";
	driver.findElement(By.id("input-firstname")).sendKeys("  "+EssentialFunctions.PropertyReaderForQAfox("firstName")+"  ");
	driver.findElement(By.id("input-lastname")).sendKeys("  "+EssentialFunctions.PropertyReaderForQAfox("lastName")+"  ");
	driver.findElement(By.id("input-email")).sendKeys("  "+EssentialFunctions.generateEmail()+"");
	driver.findElement(By.id("input-telephone")).sendKeys("  "+EssentialFunctions.PropertyReaderForQAfox("telephoneNumber")+"  ");
	driver.findElement(By.id("input-password")).sendKeys("  "+EssentialFunctions.PropertyReaderForQAfox("validPassword")+"  ");
	driver.findElement(By.id("input-confirm")).sendKeys("  "+EssentialFunctions.PropertyReaderForQAfox("validPassword")+"  ");
	driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
	driver.findElement(By.xpath("//input[@type='checkbox' and @name='agree' and @value='1']")).click();
	driver.findElement(By.xpath("//input[@type='submit' and  @value='Continue']")).click();	
	driver.findElement(By.xpath("//div[@class='list-group']/a[text()='My Account']")).click();
	driver.findElement(By.xpath("//div[@id='content']//a[text()='Edit your account information']")).click();
	String actualFirstname=driver.findElement(By.id("input-firstname")).getDomAttribute("value");
	String actualLastName=driver.findElement(By.xpath("//input[@type='text' and @id='input-lastname']")).getDomAttribute("value");
    //ast.assertEquals(actualFirstname,enterName.trim());
    //ast.assertEquals(actualLastName, lastName.trim());
    ast.assertEquals(actualFirstname,EssentialFunctions.PropertyReaderForQAfox("firstName"));
    ast.assertEquals(actualLastName, EssentialFunctions.PropertyReaderForQAfox("lastName"));
    ast.assertAll();
	}
	
	/*This test case checks if privacy checkbox is not selected by default*/
	@Test(priority=20)
	public void verifyRegistryPageWithoutPrivacyFieldCheckBox()
	{
	 //expects false but output is false ie checkbox is not selected
	 Assert.assertFalse(driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).isSelected());
	 }
	
	
	/*This test case verifies if account do not get registered by not selecting privacy select option  */
	@Test(priority=21)
	public void verifyRegistryAcctWithoutPrivacyFieldSelected() throws IOException
	{
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and  @value='Continue']")).click();
		
	}
	
	
	/*This test case if password and confirm password field are toggled to hide visibility- 
	 * Here we verify attribute value of type if its password we see toggled and if its text we dont see toggled*/	
	@Test(priority=22)
	public void verifyPasswordToggledOnRegistryPage()
	{
   	String st = driver.findElement(By.id("input-password")).getDomAttribute("type");
	Assert.assertEquals(st, "password");
	Assert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("type"),"password");
	driver.quit();
	}
	
	/*This test verfies all the links on register account page*/
	@Test(priority=23)
	public void verifyLinksOnRegistryPage()
	{
		
		driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Login']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Register']")).click();
		Assert.assertEquals(driver.getTitle(),"Register Account");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//li//a[text()='About Us']")).click();
		Assert.assertEquals(driver.getTitle(),"About Us");
		driver.navigate().back();
					
	}
	
	
	/*This test case verifies registering an account by filling in password field but not filling password confirm*/
	@Test
	public void verifyAccountRegistryWithUnfilledPasswordConfirm() throws IOException
	{
		
		driver.findElement(By.id("input-firstname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(EssentialFunctions.PropertyReaderForQAfox("validPassword"));
		driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and @name='agree' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'  and @value='Continue']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-sm-10']//div[@class='text-danger']")).getText(),"Password confirmation does not match password!");
		
	}
	
//This test case verifies if registring account on chrome, edge and firefox browsers*/	
//	@Test
//	public void verifyRegistringAccountOnDifferentBrowser() throws InterruptedException
//	{
//		String browsertype = "Edge";
//		WebDriver driver = null;
//		if(browsertype.equalsIgnoreCase("chrome"))
//			driver=new ChromeDriver();
//		else if(browsertype.equalsIgnoreCase("mozilla"))
//			driver=new FirefoxDriver();
//		else if(browsertype.equalsIgnoreCase("Edge"))
//			driver=new EdgeDriver();
//		driver.get("https://tutorialsninja.com/demo/");
//		Thread.sleep(2000);
//		
//		
//	}
	
	
	
	
	
	

}

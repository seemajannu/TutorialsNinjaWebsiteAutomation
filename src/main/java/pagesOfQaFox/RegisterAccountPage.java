package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class RegisterAccountPage extends CommonBaseClass{
	WebDriver driver;
	
	public RegisterAccountPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement inputFirstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement inputLastNameField;
	
	@FindBy(id="input-email")
	private WebElement inputEmailField;
	
	@FindBy(id="input-telephone")
	private WebElement inputTelephoneField;
	
	@FindBy(id="input-password")
	private WebElement inputValidPswd;
	
	@FindBy(id="input-confirm")
	private WebElement inputConfirmPswd;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsletterBoxyes;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	private WebElement newsletterBoxno;
	
	@FindBy(xpath="//input[@type='checkbox'][@value='1']")
	private WebElement privacyFieldchked;
	
	
	@FindBy(xpath="//input[@type='checkbox'][@value='0']")
	private WebElement privacyFieldUnchked;
	
	@FindBy(xpath="//input[@type='submit'][@value='Continue']")
	private WebElement continueButtonOnRegisterPage;
	
	@FindBy(xpath="//div[@class='text-danger'][text()='Password confirmation does not match password!']")
	private WebElement pswdConfirmMismatchErrorDisp;	
	
	
	@FindBy(xpath="//div[@class='form-group required has-error']//input[@type='text' and @name='firstname']/following-sibling::div")
	private WebElement firstNameNotifaction;
	
	@FindBy(xpath="//div[@class='form-group required has-error']//input[@type='text' and @name='lastname']/following-sibling::div")
	private WebElement lastNameNotification;
	
    @FindBy(xpath="//div[@class='form-group required has-error']//input[@type='email' and @name='email']/following-sibling::div")
	private WebElement emailNotification;
    
    @FindBy(xpath="//div[@class='form-group required has-error']//input[@type='tel' and @name='telephone']/following-sibling::div")
    private WebElement telpNotification;
    
    @FindBy(xpath="//div[@class='form-group required has-error']//input[@type='password' and @name='password']/following-sibling::div")
    private WebElement inPswdNotification;
    
    @FindBy(xpath="//ul[@class='breadcrumb']//li/a[text()='Register']")
    private WebElement registerPageBreadcrumb;
    
    public String pswdConfirmErrorDisplayOnMismatch()
    {
    	return pswdConfirmMismatchErrorDisp.getText();
    }
    
    public boolean registerPageBreadcrumbIsDisplayed()
    {
    	return registerPageBreadcrumb.isDisplayed();
    }
    
    public String inPswdErrorNotification()
    {
    	return inPswdNotification.getText();
    }
    
    public String telphErrorNotification()
    {
    	return telpNotification.getText();
    }
    
    public String emailErrorNotification()
    {
    	return emailNotification.getText();    	
    }	
	
	public String lastNameErrorNotification()
	{
		return lastNameNotification.getText();
	}
	
	public String firstNameErrorNotification()
	{
		return firstNameNotifaction.getText();
	}
	
	
	public void enterInputFirstNameBox(String name)
	{
		inputFirstNameField.sendKeys(name);
	}
	
	public void enterInputLastNameBox(String name)
	{
		inputLastNameField.sendKeys(name);
	}
	public void enterInputEmailBox(String name)
	{
		inputEmailField.sendKeys(name);
	}
	public void enterInputTelephoneBox(String name)
	{
		inputTelephoneField.sendKeys(name);
	}

	public void enterInputValidPswdBox(String name)
	{
		inputValidPswd.sendKeys(name);
	}
	public void enterInputConfirmPswdBox(String name)
	{
		inputConfirmPswd.sendKeys(name);
	}
	
	public void enterNewsLetterBox(String input)
	{
		if (input.equals("yes"))
		newsletterBoxyes.click();
		else
		newsletterBoxno.click();
	}
	
	public void enterPrivacyPolicyField(String input)
	{
		if(input.equals("yes"))
			privacyFieldchked.click();
		else
			privacyFieldUnchked.click();
			
	}
	
	public void continuebuttonClickOnRegisterPage()
	{
		continueButtonOnRegisterPage.click();
	}
}

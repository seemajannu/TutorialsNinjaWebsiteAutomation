package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class LandingPage  extends CommonBaseClass{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAcctDropdownMenu;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	public void loginMenuClick()
	{
		loginOption.click();
	}
	
	public void acctMenuClick()
	{
		myAcctDropdownMenu.click();
	}
	
	public void regOptionSelect()
	{
		registerOption.click();
	}
	

}

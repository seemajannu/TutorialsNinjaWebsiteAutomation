package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class AccountSuccessPage extends CommonBaseClass{
	
	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='content']//h1")
	private WebElement successPageText;
	
	@FindBy(linkText="Account")
	private WebElement successPageContinueButton;
	
	public  String successpageContent()
	{
		return successPageText.getText();
	}
	
	public void successPageContinueClick()
	{
		successPageContinueButton.click();
	}
	
	

}

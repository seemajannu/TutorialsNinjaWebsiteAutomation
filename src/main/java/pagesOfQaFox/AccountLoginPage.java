package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class AccountLoginPage extends CommonBaseClass {
	
	WebDriver driver;
	
	public AccountLoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='btn btn-primary' and text()='Continue']")
	private WebElement newCustomerRegAcctContinueBtn;
	
	public void newCustomerContinueBtnClick()
	{
		newCustomerRegAcctContinueBtn.click();
	}

	

}

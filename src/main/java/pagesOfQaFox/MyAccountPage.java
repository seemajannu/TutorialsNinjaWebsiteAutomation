package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class MyAccountPage extends CommonBaseClass{
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Account")
	private WebElement accountBreadcrumb;
	
	@FindBy(xpath="//ul[@class='list-unstyled']/li/a[text()='Subscribe / unsubscribe to newsletter']")
	private WebElement subUnsubNewslettermenu;
	
	public void subUnsubNewsLetterMenuClick()
	{
		subUnsubNewslettermenu.click();
	}
	public boolean accountBreadCrumbIsDisplayed()
	{
		return accountBreadcrumb.isDisplayed();
	}

}

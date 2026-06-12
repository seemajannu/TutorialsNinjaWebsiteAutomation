package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class RightColumnPage extends CommonBaseClass{
	WebDriver driver;
	
	public RightColumnPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Register']")
	private WebElement registerRightMenuOption;
	
	public void registerMenuOptionRightColumClick()
	{
		registerRightMenuOption.click();
	}

}

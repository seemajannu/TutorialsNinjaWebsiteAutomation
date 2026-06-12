package pagesOfQaFox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.CommonBaseClass;

public class NewsLetterPage extends CommonBaseClass{
	
     WebDriver driver;

	public NewsLetterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='col-sm-10']//input[@type='radio' and @value='1']")
	private WebElement newsLetterSubscriptionRadioBtnYes;
	
	@FindBy(xpath="//div[@class='col-sm-10']//input[@type='radio' and @value='0']")
	private WebElement newsLetterSubscriptionRadioBtnNo;
	
	public boolean newLetterSubscriptionRadioBtnSelection(String input)
	{
		boolean inputValue=true;
		if(input.equals("yes"))
		  inputValue=newsLetterSubscriptionRadioBtnYes.isDisplayed();			
		else if(input.equals("no"))
			inputValue=newsLetterSubscriptionRadioBtnNo.isDisplayed();
		return inputValue;
	}


}

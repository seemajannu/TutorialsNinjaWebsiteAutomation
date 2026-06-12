package baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonBaseClass {
	
	WebDriver driver;
	
	public CommonBaseClass(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebDriver getPageDriver()
	{
		return driver;
	}

}

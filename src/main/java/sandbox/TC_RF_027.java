package sandbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TC_RF_027 {
/*This test case verifies if registring account on chrome, edge and firefox browsers*/
	
	@Test
	public void verifyRegistringAccountOnDifferentBrowser() throws InterruptedException
	{
		String browsertype = "Edge";
		WebDriver driver = null;
		if(browsertype.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(browsertype.equalsIgnoreCase("mozilla"))
			driver=new FirefoxDriver();
		else if(browsertype.equalsIgnoreCase("Edge"))
			driver=new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		
		
	}
}

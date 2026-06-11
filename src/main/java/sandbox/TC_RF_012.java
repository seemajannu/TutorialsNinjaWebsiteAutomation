package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonUtilities.EssentialFunctions;

public class TC_RF_012 {
	/*This test case registers user account with keyboard keys*/
	
	Actions act;
		@Test
	public void verifyRegAcctUsingKeyboardKeys() throws InterruptedException
	{
		
	 WebDriver driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	 driver.get("https://tutorialsninja.com/demo/");
	 act=new Actions(driver);
	 keyboardClicks(3);
	 act.sendKeys(Keys.ARROW_DOWN).build().perform();
	 driver.findElement(By.linkText("Register")).click();
	 keyboardClicks(23);
	 Thread.sleep(2000); 
	 act.sendKeys("skj").sendKeys(Keys.TAB).sendKeys("ja").sendKeys(Keys.TAB).sendKeys(EssentialFunctions.generateEmail()).sendKeys(Keys.TAB).sendKeys("12345").sendKeys(Keys.TAB).sendKeys("1234").sendKeys(Keys.TAB).sendKeys("1234").sendKeys(Keys.TAB).build().perform();
	 keyboardClicks(2);
	 Thread.sleep(3000);
	 act.sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
	 Thread.sleep(2000);	
	 keyboardClicks(21);
	 Assert.assertEquals(driver.findElement(By.linkText("Success")).getText(), "Success", "The Success page is landed");
	 //The above message gets displayed only when asserts value is not equal
	 
	}
		
	public void keyboardClicks(int numofClicks)
	{
		for(int j=0;j<numofClicks; j++)
					act.sendKeys(Keys.TAB);
		
		act.build().perform();
		System.out.println("mouse clicked");
	}
	
	
	}
	
	



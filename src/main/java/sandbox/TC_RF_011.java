package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonUtilities.EssentialFunctions;

public class TC_RF_011 {
	/*This test case verifies registering an acct by providing invalid phone number */
	
	@Test
	public void verifyRegistringAcctByInvalidPhoneNumber()
	{
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.get("https://tutorialsninja.com/demo/");
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.id("input-firstname")).sendKeys("skj");
	driver.findElement(By.id("input-lastname")).sendKeys("jam");
	driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
	driver.findElement(By.id("input-telephone")).sendKeys("1abcde");
	driver.findElement(By.id("input-password")).sendKeys("1234");
	driver.findElement(By.id("input-confirm")).sendKeys("1234");
	driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
	driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
	driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();	
	Assert.assertFalse(driver.findElement(By.linkText("Success")).isDisplayed(), "The acct is successfully created with incorrect telephone number");
	}

}

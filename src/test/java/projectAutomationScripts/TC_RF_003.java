package projectAutomationScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

	public class TC_RF_003 
	{
		/* This test registers an account with valid details and goes to account success page .Then navigates to further page and verifies
		 if its on Account page
		 */
		@Test	
		public void verifyRegistrationWithAllAcctDetails()
		{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");	
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys("arun.m31@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234569");
		driver.findElement(By.id("input-password")).sendKeys("1234569");
		driver.findElement(By.id("input-confirm")).sendKeys("1234569");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
		
		String successfullmessexpected="Your Account1 Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(),successfullmessexpected);
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Account")).isDisplayed());
				
		}
	}

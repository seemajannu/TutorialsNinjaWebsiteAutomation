package projectAutomationScripts;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {

	@Test
	public void verifyRegistrationByMandatory()
	{
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.get("https://tutorialsninja.com/demo/");
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.id("input-firstname")).sendKeys("Sma");
	driver.findElement(By.id("input-lastname")).sendKeys("jan");
	driver.findElement(By.id("input-email")).sendKeys("ielngln123@gmail.com");
	driver.findElement(By.id("input-telephone")).sendKeys("1234569");
	driver.findElement(By.id("input-password")).sendKeys("12345");
	driver.findElement(By.id("input-confirm")).sendKeys("12345");
	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	driver.findElement(By.xpath("//input[@type='checkbox'][@value='1']")).click();
	driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
	String expectedacctsuccessstring="Your Account Has Been Created!";
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(),expectedacctsuccessstring );
	}

}

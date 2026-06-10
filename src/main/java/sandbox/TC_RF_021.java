package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commonUtilities.EssentialFunctions;

public class TC_RF_021 {

/*This test case verifies if account do not get registered by not selecting privacy select option  */

	@Test
	public void verifyRegistryAcctWithoutPrivacyFieldSelected()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Asmngjg");
		driver.findElement(By.id("input-lastname")).sendKeys("sjanjndf");
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("123899485");
		driver.findElement(By.id("input-password")).sendKeys("1234");
		driver.findElement(By.id("input-confirm")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and  @value='Continue']")).click();
		
	}

}

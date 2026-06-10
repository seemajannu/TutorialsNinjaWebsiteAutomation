package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonUtilities.EssentialFunctions;

@Test
public class TC_RF_024 {
/*This test case verifies registering an account by filling in password field but not filling password confirm*/
	public void verifyAccountRegistryWithUnfilledPasswordConfirm()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Abcjdj");
		driver.findElement(By.id("input-lastname")).sendKeys("asdaasd");
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("123834524");
		driver.findElement(By.id("input-password")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and @name='agree' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit'  and @value='Continue']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-sm-10']//div[@class='text-danger']")).getText(),"Password confirmation does not match password!");
		
	}
}

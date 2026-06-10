package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_023 {
	/*This test verfies all the links on register account page*/
	@Test
	public void verifyLinksOnRegistryPage()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Login']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Register']")).click();
		Assert.assertEquals(driver.getTitle(),"Register Account");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//li//a[text()='About Us']")).click();
		Assert.assertEquals(driver.getTitle(),"About Us");
		driver.navigate().back();
		
		driver.quit();
		
	}

}

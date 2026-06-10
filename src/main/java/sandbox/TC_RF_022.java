package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_022 {
	/*This test case if password and confirm password field are toggled to hide visibility- 
	 * Here we verify attribute value of type if its password we see toggled and if its text we dont see toggled*/
	
	@Test
	public void verifyPasswordToggledOnRegistryPage()
	{
    WebDriver driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    driver.get("https://tutorialsninja.com/demo/");
    driver.findElement(By.xpath("//a/span[text()='My Account']")).click();
    driver.findElement(By.linkText("Register")).click();
   	String st = driver.findElement(By.id("input-password")).getDomAttribute("type");
	Assert.assertEquals(st, "password");
	Assert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("type"),"password");
	driver.quit();
	}
	

}

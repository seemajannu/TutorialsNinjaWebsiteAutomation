package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_020 {
	/*This test case checks if privacy checkbox is not selected by default*/
	@Test
	public void verifyRegistryPageWithoutPrivacyFieldCheckBox()
	{
	 WebDriver driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	 driver.get("https://tutorialsninja.com/demo/");
	 driver.findElement(By.xpath("//span[text()='My Account']")).click();
	 driver.findElement(By.linkText("Register")).click();
	 //expects true but output is false ie checkbox is not selected
	 Assert.assertTrue(driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).isSelected());
	 }
}

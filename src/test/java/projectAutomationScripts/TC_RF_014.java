package projectAutomationScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_014 {
	/*This test case verifies if all mandatory fields are marked with red color * symbol*/
	@Test
	public void verifyRegisterAcctFieldsMarkedWithRedColor()
	{
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.get("https://tutorialsninja.com/demo/");
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	
	WebElement elemcss = driver.findElement(By.cssSelector("label[for='input-firstname']"));
	JavascriptExecutor js = (JavascriptExecutor) driver;

	String content = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
	    elemcss);

	System.out.println(content);
	Assert.assertEquals(content, "rgb(255, 0, 0)");
	
	}

}

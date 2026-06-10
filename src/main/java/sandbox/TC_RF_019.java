package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtilities.EssentialFunctions;

public class TC_RF_019 {
/*This test case verifies if entered credentials with leading and trailing spaces in Registry page gets automatically trimmed off*/
	WebDriver driver;
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test
	public void verifyRegistryPageWithLeadingAndTrailingSpaces()
	{
	driver=new ChromeDriver();
	SoftAssert ast=new SoftAssert();
	String enterName="   sm   ";
	String lastName="   jhb   ";
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.get("https://tutorialsninja.com/demo/");
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.id("input-firstname")).sendKeys(enterName);
	driver.findElement(By.id("input-lastname")).sendKeys(lastName);
	driver.findElement(By.id("input-email")).sendKeys("  "+EssentialFunctions.generateEmail()+"");
	driver.findElement(By.id("input-telephone")).sendKeys("    12389000765   ");
	driver.findElement(By.id("input-password")).sendKeys("   1234   ");
	driver.findElement(By.id("input-confirm")).sendKeys("   1234   ");
	driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
	driver.findElement(By.xpath("//input[@type='checkbox' and @name='agree' and @value='1']")).click();
	driver.findElement(By.xpath("//input[@type='submit' and  @value='Continue']")).click();	
	driver.findElement(By.xpath("//div[@class='list-group']/a[text()='My Account']")).click();
	driver.findElement(By.xpath("//div[@id='content']//a[text()='Edit your account information']")).click();
	String actualFirstname=driver.findElement(By.id("input-firstname")).getDomAttribute("value");
	String actualLastName=driver.findElement(By.xpath("//input[@type='text' and @id='input-lastname']")).getDomAttribute("value");
    ast.assertEquals(actualFirstname,enterName.trim());
    ast.assertEquals(actualLastName, lastName.trim());
    ast.assertAll();
	}
	
}

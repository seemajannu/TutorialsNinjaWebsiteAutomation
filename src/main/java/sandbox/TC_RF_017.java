package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtilities.EssentialFunctions;

public class TC_RF_017 {
	/*This test case implements data driven testing concept wherein it verifies if application adheres registering acct using complex standard passwords*/
	WebDriver driver;
	
	@AfterMethod
	public void teardown()
	{
		driver.close();
	}
	
	@Test(dataProvider="PasswordSupplyList")	
	public void verifyPasswordStandardsForRegisterFunctionality(String pwd)
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Sma");
		driver.findElement(By.id("input-lastname")).sendKeys("jds");
		driver.findElement(By.id("input-email")).sendKeys(EssentialFunctions.generateEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("12348767");
        driver.findElement(By.xpath("//input[@type='password' and @id='input-password']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@type='password' and @id='input-confirm']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter'and @value='1']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox' and  @value='1']")).click();
        driver.findElement(By.xpath("//input[@type='submit' ]")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='content']//h1")).isDisplayed());        
      }

	@DataProvider(name="PasswordSupplyList")
	 public Object[] passwordsupplier()
	 {
		 Object[] pswd= {"12345","abcdefghi","abcd1234","abcd123$","ABCD456#"};
		 return pswd;
		 
	 }
}

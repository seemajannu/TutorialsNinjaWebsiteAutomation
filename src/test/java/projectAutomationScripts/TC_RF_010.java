package projectAutomationScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

public class TC_RF_010
{
	
/* This test case verifies registration of account by providing invalid emails in email field
 *Also note how soft and hard assert changes output of test
 */
	@Test
	public void verifyRegisterAcctWithInvalidEmail() throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		//SoftAssert ast=new SoftAssert();
		
		String expectedmsg1="Please include an '@' in the email address. 'amotoori' is missing an '@'.";
		String expectedmsg2="Please nter a part following '@'. 'amotoori@' is incomplete.";
		//String expectedmsg2="Please Enter a part following '@'. 'amotoori@' is incomplete.";
		String expectedmsg3="E-Mail Address does not appear to be valid!";
		String expectedmsg4="'.' is used at a wrong position in 'gmail.'.";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys("amotoori");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//ast.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg1);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg1);
		System.out.println("part 1 of test  passed");
		
		//mistake in 2
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//ast.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg2);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg2);
		System.out.println("part 2 of test  passed");
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//ast.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger' and (text()='E-Mail Address does not appear to be valid!')]")).getText(), expectedmsg3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger' and (text()='E-Mail Address does not appear to be valid!')]")).getText(), expectedmsg3);
		//System.out.println(driver.findElement(By.xpath("//input[@id='input-email' ]")).getText());
		System.out.println("part 3 of test  passed");
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//ast.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg4);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"), expectedmsg4);
		System.out.println("part 4 of test  passed");
		
		//ast.assertAll();
		
		//Please include an '@' in the email address. 'amotoori' is missing an '@'.
		//Please enter a part following '@'. 'amotoori@' is incomplete.
		//xpath:   //input[@id='input-email']/following-sibling::div
		//'.' is used at a wrong position in 'gmail.'.
		
	}
}

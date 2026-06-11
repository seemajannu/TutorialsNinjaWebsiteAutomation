package projectAutomationScripts;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_RF_016 {
	/*This test case verifies if mandatory fields on register page do not accept spaces and also it takes screenshot at the end*/
	@Test
	public void verifyRegistryPageWithSpaces() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		SoftAssert ast=new SoftAssert();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("     ");
		driver.findElement(By.id("input-lastname")).sendKeys("     ");
		driver.findElement(By.id("input-email")).sendKeys("     ");
		driver.findElement(By.id("input-telephone")).sendKeys("     ");
		driver.findElement(By.id("input-password")).sendKeys("     ");
		driver.findElement(By.xpath("//input[@type='password' and @id='input-password']")).sendKeys("     ");
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		String actualfirstnamemsg=driver.findElement(By.xpath("//input[@type='text' and @name='firstname']/following-sibling::div")).getText();
		String expectedfirstnamemsg="First Name must be between 1 and 32 characters!";
		ast.assertEquals(actualfirstnamemsg, expectedfirstnamemsg);
		String actuallastnamemsg=driver.findElement(By.xpath("//input[@type='text' and @name='lastname']/following-sibling::div")).getText();
		String expectedlastnamemsg="Last Name must be between 1 and 32 characters!";
		ast.assertEquals(actuallastnamemsg, expectedlastnamemsg);
		String actualemailmsg=driver.findElement(By.xpath("//input[@type='email' and @name='email']/following-sibling::div")).getText();
		String expectedemailmsg="E-Mail Address does not appear to be valid!";
		ast.assertEquals(actualemailmsg, expectedemailmsg);
		ast.assertAll();
		Thread.sleep(2000);
		TakesScreenshot sct=(TakesScreenshot)driver;	
		 File filecpy = sct.getScreenshotAs(OutputType.FILE);
		 File fp=new File("C:\\Users\\itscm\\OneDrive\\Desktop\\Live Project work\\snap.png");
	    try {
			FileUtils.copyFile(filecpy, fp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}

}

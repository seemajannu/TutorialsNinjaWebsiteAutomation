package sandbox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_RF_013 {
	/*This test case verifies if all fields on register account page has proper placeholder*/
	
	@Test
	public void verifyAcctRegistryPageForPlaceHolders()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		SoftAssert ast=new SoftAssert();
		
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("placeholder"), "First Name");
		//Note xpath for placeholder First Name, value is different tag and has no value next is placeholder tag with first name
		//driver.findElements(By.xpath("//input[ @ placeholder='First Name']"));
		
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("placeholder"), "Last Name");
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='E-Mail']")).getAttribute("placeholder"), "E-Mail");
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Telephone']")).getAttribute("placeholder"), "Telephone");
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("placeholder"), "Password");
		
		ast.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password Confirm']")).getAttribute("placeholder"), "Password Confirm");
		ast.assertAll();
	}

}

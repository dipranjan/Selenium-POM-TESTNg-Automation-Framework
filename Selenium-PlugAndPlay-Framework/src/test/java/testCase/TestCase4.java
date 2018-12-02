/**
 * 
 */
package testCase;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;

public class TestCase4 extends  BaseClass{
	
	@Test(groups="smoke",description="Invalid Login Flipkart")
	public static void testCase4() {
		
		HomePage homePage = new HomePage();
		driver.get("https://www.flipkart.com");
		homePage.username.sendKeys("testuser");
		homePage.password.sendKeys("testpassword");
		homePage.password.sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(.,'valid')]")).getText(), "Please enter valid Email ID/Mobile number");		
	}

}

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

public class TestCase5 extends  BaseClass{
	
	@Test(groups="smoke",description="This test case will fail")
	public static void testCase5() {
		
		HomePage homePage = new HomePage();
		driver.get("https://www.flipkart.com");
		homePage.username.sendKeys("testuser");
		homePage.password.sendKeys("testpassword");
		homePage.password.sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(.,'valid')]")).getText(), "Fail");		
	}

}

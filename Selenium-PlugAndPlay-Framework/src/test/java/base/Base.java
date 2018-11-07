/**
 * 
 */
package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;	
import org.testng.annotations.*;

/**
 * 	@author Dip Ranjan Chatterjee
 *	@version 1.0
 */
public class Base {
	
	public static WebDriver driver;
	public static Logger log;	
	public static Properties properties = new Properties();
	
	@SuppressWarnings("static-access")
	@BeforeSuite(description="Framework initilization")
	public static void BeforeSuite() throws FileNotFoundException, IOException {
		
		properties.load(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\configuration.properties"));
		
		//Starting the logs as per specification in the configuration file.
		if(properties.getProperty("logs").equalsIgnoreCase("yes")) {
			System.setProperty("path", System.getProperty("user.dir"));
			log.getLogger("rootLogger");
			log.debug("Logs initialized");
		}
	}
	
	@BeforeTest(description="Driver initilization and pre test setup")
	public void BeforeTest() {
		//Setting up the browser as per specification in the configuration file.
		String browser = properties.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("HTMLUnitDriver")) {
			driver = new HtmlUnitDriver();
		}
		if(properties.getProperty("cookies").equalsIgnoreCase("clear")) {
			driver.manage().deleteAllCookies();
		}		
	}
	
	@AfterTest(description="Driver teardown")
	public void AfterTest() {
		driver.quit();
	}
}

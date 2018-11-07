/**
 * 
 */
package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;



/**
 * 	@author Dip Ranjan Chatterjee
 *	@version 1.0
 */
public class BaseClass {
	
	public static WebDriver driver;
	public static Logger log=Logger.getLogger("rootLogger");;	
	public static Properties properties = new Properties();
	
	
	@BeforeSuite(description="Framework initilization")
	public static void BeforeSuite() throws FileNotFoundException, IOException {
		
		properties.load(new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configuration.properties"));
		
		//Starting the logs as per specification in the configuration file.
			//System.setProperty("logPath", System.getProperty("user.dir"));
			log.debug("Logs initialized");
	}
	
	@BeforeTest(description="Driver initilization and pre test setup")
	public void BeforeTest() {
		//Setting up the browser as per specification in the configuration file.
		String browser = properties.getProperty("browser");
		String browserVersion = properties.getProperty("browser-version");
		log.debug("Setting up "+browser + " version : "+browserVersion);
		WebDriverManager.config().setForceCache(true);
		if(browser.equalsIgnoreCase("chrome")) {
			if(browserVersion.equalsIgnoreCase("null")) {
				WebDriverManager.chromedriver().setup();
			}
			else {
				try {
					WebDriverManager.chromedriver().version(browserVersion).setup();
				}
				catch(Exception e) {
					log.error(browser+" "+browserVersion+" not available, defaulting to latest version.");
					WebDriverManager.chromedriver().setup();
				}
			}			
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			if(browserVersion.equalsIgnoreCase("null")) {
				WebDriverManager.firefoxdriver().setup();
			}
			else {
				try {
					WebDriverManager.firefoxdriver().version(browserVersion).setup();
				}
				catch(Exception e) {
					log.error(browser+" "+browserVersion+" not available, defaulting to latest version.");
					WebDriverManager.firefoxdriver().setup();
				}
			}			
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("phantomjs")) {
			if(browserVersion.equalsIgnoreCase("null")) {
				WebDriverManager.phantomjs().setup();
			}
			else {
				try {
					WebDriverManager.phantomjs().version(browserVersion).setup();
				}
				catch(Exception e) {
					log.error(browser+" "+browserVersion+" not available, defaulting to latest version.");
					WebDriverManager.phantomjs().setup();
				}
			}
			Capabilities caps = new DesiredCapabilities();
			((DesiredCapabilities) caps).setJavascriptEnabled(true);
			((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
			driver = new PhantomJSDriver(caps);
			
			if(properties.getProperty("cookies").equalsIgnoreCase("clear")) {driver.manage().deleteAllCookies();}
			driver.manage().window().maximize();
		}		
	}
	
	@AfterTest(description="Driver teardown")
	public void AfterTest() {
		//driver.quit();
		log.debug("Driver quit.");
	}
}

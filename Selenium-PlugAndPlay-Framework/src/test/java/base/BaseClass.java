
package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utilities.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Logger log=Logger.getLogger("rootLogger");;	
	public static Properties properties = new Properties();
	
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	protected final static int qos = 2;
	protected final static String topic = properties.getProperty("topic");
	protected static final String serverUri = properties.getProperty("serverUri");
	protected static final String username = properties.getProperty("username");
    protected static final String password = properties.getProperty("password");
    protected static final String clientId = properties.getProperty("clientId");
    protected static MqttClient client;
	
	
	@BeforeSuite(description="Suite started")
	public static void BeforeSuite() throws FileNotFoundException, IOException {		
		properties.load(new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configuration.properties"));		
		log.debug("Logs initialized");
		log.debug("Suite Started");
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
		rep.endTest(test);
		driver.quit();
		log.debug("Driver quit.");
	}
	
	@AfterSuite(description="Suite ended")
	public void AfterSuite() {
		rep.flush();
		log.debug("Report Flushed");
		log.debug("Suite ended");
	}
}

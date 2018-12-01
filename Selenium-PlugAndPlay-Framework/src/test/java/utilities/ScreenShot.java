package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import base.BaseClass;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;

@SuppressWarnings("restriction")
public class ScreenShot extends BaseClass{
	
	static String screenshotPath;
	static String screenShotType;
	static String screenShotPath;
	static Screenshot screenshot;
	
	public static String TakeScreenShot() {
		screenShotType= properties.getProperty("screenShotType");
		screenshotPath=properties.getProperty("screenshotPath");
		if(properties.getProperty("screenshotPath").equalsIgnoreCase("null")) {
			screenshotPath=System.getProperty("user.dir") +"/Screenshots/";
		}
		if(screenShotType.equalsIgnoreCase("FullPage")||screenShotType.equalsIgnoreCase("VisibleArea")) {
			ScreenShot.ScreenShotCapture();
		}		
		return screenShotPath;
	}
	
	@SuppressWarnings({"restriction"})
	private static void ScreenShotCapture(){
		try {
			if(screenShotType.equalsIgnoreCase("FullPage")) {
				screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			}
			else if(screenShotType.equalsIgnoreCase("VisibleArea")){
				screenshot = new AShot().takeScreenshot(driver);
			}
		screenShotPath = screenshotPath+new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date())+".png";
		ImageIO.write(screenshot.getImage(),"PNG",new File(screenShotPath));
		log.debug("Screenshot taken: "+screenShotPath);		
		}
		catch(Exception e) {
			log.error("Issue with taking screenshot: "+e.getMessage().toString());
			screenShotPath=null;
		}
		
	}

}

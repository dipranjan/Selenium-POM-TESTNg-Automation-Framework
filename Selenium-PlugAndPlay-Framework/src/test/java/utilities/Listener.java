/**
 * 
 */
package utilities;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import messagingServices.*;

/**
 * @author ACER
 *
 */
public class Listener extends ScreenShot implements ITestListener, ISuiteListener{

	public void onTestStart(ITestResult result) {
		log.debug(result.getName().toUpperCase()+" Started");
		MqttMessagingServices.PublishMessage(result.getName().toUpperCase()+" Started");
	}

	public void onTestSuccess(ITestResult result) {
		log.debug(result.getName().toUpperCase()+" Passed");
		String path = ScreenShot.TakeScreenShot();
		MqttMessagingServices.PublishMessage(result.getName().toUpperCase()+" Passed");
	}

	public void onTestFailure(ITestResult result) {
		log.error(result.getName().toUpperCase()+" Failed ::" + result.getThrowable().getMessage().toString());
		
	}

	public void onTestSkipped(ITestResult result) {
		log.debug(result.getName().toUpperCase()+" Skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		log.debug(suite.getName().toUpperCase()+" Suite Started");
		MqttMessagingServices.PublishMessage(suite.getName().toUpperCase()+" Suite Started");
		
	}

	public void onFinish(ISuite suite) {
		log.debug(suite.getName().toUpperCase()+" Suite Finished");
		MqttMessagingServices.PublishMessage(suite.getName().toUpperCase()+" Suite Finished");
	}

	


}

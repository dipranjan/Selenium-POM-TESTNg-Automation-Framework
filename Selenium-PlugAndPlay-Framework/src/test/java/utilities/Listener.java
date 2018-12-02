/**
 * 
 */
package utilities;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import messagingServices.*;


public class Listener extends ScreenShot implements ITestListener, ISuiteListener{

	public void onTestStart(ITestResult result) {
		log.debug(result.getName().toUpperCase()+" Started");
		MqttMessagingServices.PublishMessage(result.getName().toUpperCase()+" Started");
		test = rep.startTest(result.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {
		log.debug(result.getName().toUpperCase()+" Passed");		
		MqttMessagingServices.PublishMessage(result.getName().toUpperCase()+" Passed");	
		test.log(LogStatus.PASS, test.addScreenCapture(ScreenShot.TakeScreenShot()));
	}

	public void onTestFailure(ITestResult result) {
		log.error(result.getName().toUpperCase()+" Failed ::" + result.getThrowable().getMessage().toString());
		MqttMessagingServices.PublishMessage(result.getName().toUpperCase()+" Failed");	
		test.log(LogStatus.FAIL, test.addScreenCapture(ScreenShot.TakeScreenShot()));
	}

	public void onTestSkipped(ITestResult result) {
		log.debug(result.getName().toUpperCase()+" Skipped");
		MqttMessagingServices.PublishMessage(result.getName().toUpperCase()+" Skipped");	
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipped the test");	
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
		//MqttMessagingServices.PublishMessage(suite.getName().toUpperCase()+" Suite Started"); -- has issue need to fix
		
	}

	public void onFinish(ISuite suite) {
		log.debug(suite.getName().toUpperCase()+" Suite Finished");
		MqttMessagingServices.PublishMessage(suite.getName().toUpperCase()+" Suite Finished");
	}

	


}

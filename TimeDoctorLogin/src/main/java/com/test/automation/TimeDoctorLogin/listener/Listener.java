package com.test.automation.TimeDoctorLogin.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.test.automation.TimeDoctorLogin.testBase.TestBase;

public class Listener extends TestBase implements ITestListener{
	
	/*WebDriver driver;
	public Listener(WebDriver driver)
	{
		this.driver=driver;
	}
*/
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();
		
		if(result.isSuccess())
		{
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
			try
			{
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/com/test/automation/wpERP/screenshot/";
				File destFile = new File((String) reportDirectory + methodName + "_" + format.format(calander.getTime())+".png");
				FileUtils.copyFile(srcFile, destFile);
				//This will help us to link the screen shot in testNG report
				Reporter.log("<a href'"+destFile.getAbsolutePath()+"'> <img src='"+ destFile.getAbsolutePath()+ "'hight='500' width='500'/> </a>");
			
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();
		
		if(!result.isSuccess())
		{
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
			try
			{
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/com/test/automation/wpERP/screenshot/";
				File destFile = new File((String) reportDirectory + methodName + "_" + format.format(calander.getTime())+".png");
				FileUtils.copyFile(srcFile, destFile);
				//This will help us to link the screen shot in testNG report
				Reporter.log("<a href'"+destFile.getAbsolutePath()+"'> <img src='"+ destFile.getAbsolutePath()+ "'hight='500' width='500'/> </a>");
			
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
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

}

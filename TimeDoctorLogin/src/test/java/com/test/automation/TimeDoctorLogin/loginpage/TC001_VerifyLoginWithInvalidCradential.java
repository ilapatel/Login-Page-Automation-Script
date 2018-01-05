package com.test.automation.TimeDoctorLogin.loginpage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.TimeDoctorLogin.testBase.TestBase;
import com.test.automation.TimeDoctorLogin.uiAction.LoginPage;

public class TC001_VerifyLoginWithInvalidCradential extends TestBase{
	

	public static final Logger log = Logger.getLogger(TC001_VerifyLoginWithInvalidCradential.class.getName());
	LoginPage loginpage;

	@BeforeTest
	public void setup() throws InterruptedException
	{
		init();				

	}
	
	@Test
	public void VerifyLoginWithInvalidCradential() throws InterruptedException
	{
		log.info("===================Starting the VerifyLoginWithInvalidCradential=================");
		loginpage = new LoginPage(driver);
		loginpage.loginToERP("xyz@abc.im", "password");
		Assert.assertEquals(loginpage.getInvalidTextMessage(),"Invalid Email Or Password");
		log.info("===================Ending the VerifyLoginWithInvalidCradential=================");
	}
	
	@AfterTest
	public void endTest()
	{
		driver.quit();
	}
}

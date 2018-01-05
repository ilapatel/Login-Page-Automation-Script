package com.test.automation.TimeDoctorLogin.uiAction;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.TimeDoctorLogin.testBase.TestBase;

public class LoginPage extends TestBase{
	
	public static final Logger log = Logger.getLogger( LoginPage.class.getName());

	WebDriver driver;
	
	@FindBy(id="email")
	WebElement usernametxt;
	
	@FindBy(id="password")
	WebElement Elpassword;
	
	@FindBy(id="signinFormButton")
	WebElement ElsigninFormButton;
	
	//@FindBy(xpath="//*[@id='messageBox']")
	//WebElement invalidationLoginMessage;
	
	@FindBy(xpath="//span[@title='Report List']")
	WebElement redirectToERPSystemReportPage;
	
	//Logout element
	@FindBy(xpath="html/body/div[1]/div/aside/div/div[2]/div[1]/img")
	WebElement profilebutton;
	
	@FindBy(xpath="//*[@title='Log Out']")
	WebElement logoutBtn;
	
	public LoginPage(WebDriver driver) 
	{
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginToERP(String username,String password)
	{
		usernametxt.sendKeys(username);
		log.info("Enter email address "+username+" and object is "+usernametxt.toString());
		Elpassword.sendKeys(password);
		log.info("Enter password "+password+" and object is "+Elpassword.toString());
		log.info("Click on Submit button and object is "+ElsigninFormButton.toString());
		ElsigninFormButton.click();
	}
	
	public String getInvalidTextMessage() throws InterruptedException 
	{
		Thread.sleep(2000);
		WebElement invalidationLoginMessage = driver.findElement(By.xpath("//div[@id='signinFormMiddle']//following-sibling::div[@id='messageBox']"));
		log.info("Error message is "+invalidationLoginMessage.getText());
		return invalidationLoginMessage.getText();
	}
	
	public String getValidTextMessage() 
	{
		log.info("Redirect on Report List Page is "+redirectToERPSystemReportPage.getText());
		return redirectToERPSystemReportPage.getText();
	}
	
	public boolean verifyLogoutDisplay()
	{

		try {
			//waitForElement(400, profilebutton);
			Thread.sleep(2000);
			profilebutton.click();
			Thread.sleep(1000);
			//waitForElement(200, logoutBtn);
			logoutBtn.isDisplayed();
			log.info("=======Log Out is Dispay=========="+logoutBtn.toString());

			return true;
		} 
		catch (Exception e) {
			//driver.switchTo().defaultContent();
			return false;		
		}
	}
	
/*	public void clickOnLogout() throws InterruptedException
	{
		//waitForElement(300, profilebutton);
		Thread.sleep(2000);
		profilebutton.click();
		//waitForElement(100, logoutBtn);
		Thread.sleep(2000);
		logoutBtn.click();

		log.info("==============Clicked on logout button and object is :-"+logoutBtn.toString());
	}*/
}

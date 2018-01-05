package com.test.automation.TimeDoctorLogin.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.test.automation.TimeDoctorLogin.excelReader.Excel_Reader;
import com.test.automation.TimeDoctorLogin.listener.Listener;


public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriver driver;
	String url = "https://login.timedoctor.com/login";
	String browser = "firefox";
	com.test.automation.TimeDoctorLogin.excelReader.Excel_Reader excel;
	//public WebElement element;
	Listener lis;
	
	public void init()
	{
		selectBrowser(browser);
		getURL(url);
		//lis = new Listener(driver);
		String log4jconfpath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfpath);
		
	}
	
	public void selectBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Driver/geckodriver.exe");
			log.info("Creating object of"+browser);
			driver = new FirefoxDriver();
		}
	    else if(browser.equals("chrome"))
		{
		    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	public void getURL(String url)
	{
		log.info("Navigating to "+url);
		driver.get(url);
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public String[][] getData(String excelName, String sheetName)
	{
		//LoginData.xlsx
		String path = System.getProperty("user.dir")+"/src/main/java/com/test/automation/wpERP/data/"+excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(excelName, sheetName);
		return data;
	}
	
	public String[][] getLoginData(String excelName, String sheetName, int rowNum)
	{
		//LoginData.xlsx
		String path = System.getProperty("user.dir")+"/src/main/java/com/test/automation/wpERP/data/"+excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheetInSelectedRow(excelName, sheetName, rowNum);
		return data;
	}
	
	//Explicit wait method 
	public void waitForElement(int timeOutInSeconds, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void getScreenShot(String name)
	{
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try
		{
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/com/test/automation/wpERP/screenshot/";
			File destFile = new File((String) reportDirectory + name + "_" + format.format(calander.getTime())+".png");
			FileUtils.copyFile(srcFile, destFile);
			//This will help us to link the screen shot in testNG report
			Reporter.log("<a href'"+destFile.getAbsolutePath()+"'> <img src='"+ destFile.getAbsolutePath()+ "'hight='100' width='100'/> </a>");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
	
	public void clickOnMenuNavigation(String menuName,String subMenu) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@title='"+menuName+"']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@title='"+subMenu+"']")).click();
	}

}
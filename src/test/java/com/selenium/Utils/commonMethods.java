package com.selenium.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.Stepdefination.Hooks;


public class commonMethods extends Hooks{
	
		 static String commonpath = System.getProperty("user.dir");
	static String Report_Pathdefault = commonpath + "/Reports/TestAutomationReport/";
    static String Report_path = commonpath + "/Reports/TestAutomationReport/TestReports/";
   
  
    public static void savingReport() throws IOException, InterruptedException {

        File srcDir = new File(Report_Pathdefault);
        File destDir = new File(Report_path);
        FileUtils.copyDirectory(srcDir, destDir);
        String Time_Date = present_date_Time();
        File File_Change_Src = new File(destDir + "/cucumber-html-reports");
        String File_Change_New = destDir + "/Automation_Report_" + Time_Date;
        File_Change_Src.renameTo(new File(File_Change_New));
        
    }
    
    public static String present_date_Time() {

        int day, month, year;
        int second, minute, hour;
        GregorianCalendar date = new GregorianCalendar();

        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        year = date.get(Calendar.YEAR);

        second = date.get(Calendar.SECOND);
        minute = date.get(Calendar.MINUTE);
        hour = date.get(Calendar.HOUR);

        // String Time_Date = day+(month+1)+year+"_"+hour+"_"+minute+"_"+second;

        String Time_Date = day + "_" + (month + 1) + "_" + year + "_" + hour + "_" + minute + "_" + second;

        // day+(month+1)+year+"_"+hour+"_"+minute+"_"+second

        return Time_Date;

    }
    
    /**
	 * This method will clear the textfield and enter new value   
	 * @param element reference of a webelement that needs to be checked for visibility
     * @throws Exception 
	 */
    public void clearAndEnterText(WebElement webElement, String text, int timeoutInSeconds) throws InterruptedException {
		webElement.clear();	
		Thread.sleep(1000);
		webElement.sendKeys(text);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
    /**
	 * This method will click the webelement 
	 * @param element reference of a webelement that needs to be checked for visibility
     * @throws Exception 
	 */
    public static void clickWithJavaScript(String locatorName) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(locatorName));
    	Thread.sleep(1000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		setSleepTimeInSeconds(5);
	}
         

	
	public static void setSleepTimeInSeconds(int seconds) {
        try {
             Thread.sleep(seconds * 1000);
        } catch (InterruptedException var3) 
        {
            
        }
	}
	/**
	 * This method will wait for visibility of the webelement 
	 * @param element reference of a webelement that needs to be checked for visibility
     * @throws Exception 
	 */
	public static  void waitForDisplayOfWebElement(By  webElement) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
	}
	/**
	 * This method will perform visibility check of a webelement 
	 * @param element reference of a webelement that needs to be checked for visibility
     * @throws Exception 
	 */
	public static boolean isDisplayed(WebElement Element) {

		if (Element.isDisplayed() == false) {
			return false;
		} else {
			return true;
		}
	}
    /**
	 * This method will perform visibility check of a webelement 
	 * @param element reference of a webelement that needs to be checked for visibility
     * @throws Exception 
	 */
	public static  void checkElementVisible(WebElement element) throws Exception {
		Assert.assertTrue(isDisplayed(element));
	}
	
	/**
	 * This method will enter a text in the text field with clearing the text field
	 * 
	 * @param webElement reference of a webElement where value needs to be entered
	 * @param text       value that needs to be entered into the text field
	 */
	public static  void clearAndEnterText(String locator, String text) {
		WebElement webElement=driver.findElement(By.xpath(locator));
		webElement.clear();
		webElement.sendKeys(text);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}





}

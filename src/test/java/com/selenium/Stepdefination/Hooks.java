package com.selenium.Stepdefination;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.After;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.selenium.Utils.DataReader;
//import com.selenium.Utils.log;



import java.io.IOException;

public class Hooks {
    public static WebDriver driver;
    String driverPath 						= System.getProperty("user.dir")+"\\src\\";
    Scenario scenario;
    
    
    public void setUpBrowser(Scenario scenario) throws Exception{
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.get(DataReader.readPropertyUrl("application"));
	//	((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
           }
    @Before
    public void beforestartUp(Scenario scenario) throws Exception {
		//log.startTestScenario(scenario.getName());
		this.scenario = scenario;
		setUpBrowser(scenario);
	}
    
   

    @After
    public void after(Scenario scenario) throws IOException, InterruptedException {

        if (scenario.isFailed()) {
            grabVisibleScreenWithoutScrolling(driver, scenario);
        }
        driver.quit();
    }

    public static void grabVisibleScreenWithoutScrolling(WebDriver driver, Scenario scenario) {
        scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
    }
    
    
    

    
    
    
}

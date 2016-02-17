package framework.tests.positive;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserTestPage {

	WebDriver driver;
	String browserPage = "http://demo.borland.com/gmopost/browser-test.htm";
	Logger log = Logger.getLogger("framework");

	@BeforeClass
	public void startUp() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		log.debug("~~~~~~~~~~~~~~~~~~ BROWSER TEST PAGE TESTS NOW STARTING ~~~~~~~~~~~~~~~~~~~~~~~");
		driver.get(browserPage);
		log.debug("Navigating to : " + browserPage);
	}

	@AfterClass
	public void shutDown() {
		
		driver.quit();
	
	}

	@AfterMethod
	public void afterMethod(){
		log.debug("==============================================================================================");
	}
	
	@Test(description = "Enter Browser Test Page Site , Verify Current/Expected Page Title , Verify Current/Expected URL")
	public void verifyEnterBrowserTestPageSite() {
		
		log.debug(" ~~~~~~~~~~~~~~~~~~~~~ Starting Verify URL and Page Title Tests .... ~~~~~~~~~~~~~~~~~~~~~ ");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String expectedUrl = "http://demo.borland.com/gmopost/browser-test.htm";
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				log.debug("Current URL is the Expected URL - PASS");
			} else {
				log.debug("Current URL IS NOT the Expected URL - FAIL");
			}
			String expectedTitle = "Browser Test Page";
			String actualTitle = driver.getTitle();
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				log.debug("Current Title is the Expected TITLE - PASS");
			} else {
				log.debug("Current Title IS NOT the Expected TITLE - FAIL");
			}
			
		} catch (Exception e) {
			log.debug("(BROWSER TEST PAGE SITE) Exception : " + e);
		}
	}

}

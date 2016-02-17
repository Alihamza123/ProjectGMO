package framework.tests.positive;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AboutPage {

	WebDriver driver;
	String aboutPage = "http://demo.borland.com/gmopost/about.htm";
	Logger log = Logger.getLogger("framework");

	@BeforeClass
	public void startUp() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		log.debug("~~~~~~~~~~~~~~~~~~~~~ ABOUT PAGE TESTS NOW STARTING ~~~~~~~~~~~~~~~~~~~~~");
		driver.get(aboutPage);
		log.debug("Navigating to : " + aboutPage);
	}

	@AfterClass
	public void shutDown() {
		driver.quit();
		
	}

	@AfterMethod
	public void afterMethod(){
		log.debug("==============================================================================================");
	}
	
	@Test(description = "Enter About GMO Site , Verify Current/Expected Page Title , Verify Current/Expected URL")
	public void verifyEnterAboutGmoSite() {
		log.debug(" ~~~~~~~~~~~~~~~~~~~~~ Starting About Gmo Site Tests .... ~~~~~~~~~~~~~~~~~~~~~ ");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String expectedUrl = "http://demo.borland.com/gmopost/about.htm";
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				log.debug("Current URL is the Expected URL - PASS");
			} else {
				log.debug("Current URL IS NOT the Expected URL - FAIL");
			}
			String expectedTitle = "About This Site";
			String actualTitle = driver.getTitle();
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				log.debug("Current Title is the Expected TITLE - PASS");
			} else {
				log.debug("Current Title IS NOT the Expected TITLE - FAIL");
			}
			
		} catch (Exception e) {
			log.debug("(ABOUT GMO SITE) Exception : " + e);
		}

	}
}

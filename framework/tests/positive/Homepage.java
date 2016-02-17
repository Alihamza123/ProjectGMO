package framework.tests.positive;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homepage {

	WebDriver driver;
	String homepage = "http://demo.borland.com/gmopost/";
	Logger log = Logger.getLogger("framework");

	@BeforeClass
	public void startUp() {		
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~ STARTING BORLAND HOMEPAGE TESTS ~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(homepage);
		log.debug("Navigating to : " + homepage);		
	}

	@AfterClass
	public void shutDown() {
		driver.quit();		
	}
	
	@AfterMethod
	public void afterMethod(){
		log.debug("==============================================================================================");
	}

	@Test(description = "Verify Home Page Title")
	public void verifyHomeTitle() {
		log.debug("~~~~~~~~~~~~~~~~~~~~~ Starting Verify Home page Title Tests ~~~~~~~~~~~~~~~~~~~~~");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String expectedTitle = "Welcome to Green Mountain Outpost";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			log.debug("HOME PAGE TITLE VERIFICATION PASSED");
		} catch (Exception e) {
			System.out.println("Home page Title Exception :" + e);
			log.debug("Home page Title Exception :" + e);
		}
	}

	@Test(description = "Verify Home Page Text is Present", dependsOnMethods = { "verifyHomeTitle" })
	public void verifyHomepageText() {
		log.debug("~~~~~~~~~~~~~~~~~~~~~ Starting Verify Homepage Text is present  ~~~~~~~~~~~~~~~~~~~~~");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement text = driver.findElement(By.xpath("html/body/p[1]/font/em"));
			if (text.isDisplayed()) {
				log.debug("VISIBLE TEXT VERIFICATION PASSED");
			} else {
				log.debug("VISIBLE TEXT VERIFICATION FAILED");
			}
		} catch (Exception e) {
			System.out.println("Visible Text Exception" + e);
			log.debug("Visible Text Exception" + e);
		}

	}

	@Test(description = "Enter GMO Online Button (Enabled/Disabled)", dependsOnMethods = { "verifyHomepageText" })
	public void verifyEnterGmoButton() {
		log.debug("~~~~~~~~~~~~~~~~~~~~~ Starting Verify Enter GMO Button Tests ~~~~~~~~~~~~~~~~~~~~~");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement enterGmoButton = driver.findElement(By.xpath(".//*[@name='bSubmit']"));
			if (enterGmoButton.isEnabled() && enterGmoButton.isDisplayed()) {
				
				enterGmoButton.click();
				driver.navigate().back();
				log.debug("Button is Enabled , Clicked then Navigated Back.... ");
				
			} else {
				log.debug("Enter Gmo Button is DISABLED");
			}
		} catch (Exception e) {
			log.debug("Enter GMO BUTTON Exception" + e);
			System.out.println("Enter GMO BUTTON Exception" + e);
		}
	}

	@Test(description = "About GMO The Gmo Site Button (Enabled/Disabled)", dependsOnMethods = { "verifyEnterGmoButton" })
	public void verifyAboutGmoSiteButton() {
		log.debug("~~~~~~~~~~~~~~~~~~~~~ Starting Verify About GMO Button Tests ~~~~~~~~~~~~~~~~~~~~~");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement aboutGmoSiteButton = driver.findElement(By.xpath(".//*[@name='bAbout']"));
			if (aboutGmoSiteButton.isEnabled() && aboutGmoSiteButton.isDisplayed()) 
			{				
				aboutGmoSiteButton.click();
				driver.navigate().back();
				log.debug("Button is Enabled, Clicked then Navigated Back ... " );
				
			} else {
				log.debug("About Gmo Button is DISABLED");
			}
		} catch (Exception e) {
			log.debug("About Gmo Button Exception :" + e);
			System.out.println("About Gmo Button Exception :" + e);
		}
	}

	@Test(description = "Browser Test Page Button (Enabled/Disabled)", dependsOnMethods = {
			"verifyAboutGmoSiteButton" })
	public void verifyBrowserTestPageButton() {
		log.debug("~~~~~~~~~~~~~~~~~~~~~ Starting Verify Browser Test Page Button Tests ~~~~~~~~~~~~~~~~~~~~~");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement browserTestPageButton = driver.findElement(By.xpath(".//*[@name='bBrowserTest']"));
			if (browserTestPageButton.isEnabled() && browserTestPageButton.isDisplayed()) {
				
				browserTestPageButton.click();
				driver.navigate().back();
				log.debug("Button is Enabled , Clicked then Navigated Back .... ");
				
			} else {
				log.debug("Browser Test Page Button is DISABLED");
			}
		} catch (Exception e) {
			log.debug("Browser Test Page Button Exception :" + e);
			System.out.println("Browser Test Page Button Exception :" + e);
		}
	}

}

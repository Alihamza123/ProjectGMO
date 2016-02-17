package framework.tests.positive;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmoPage {

	WebDriver driver;
	String gmoPage = "http://demo.borland.com/gmopost/online-catalog.htm";
	Logger log = Logger.getLogger("framework");

	@BeforeClass
	public void startUp() {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TESTS NOW STARTING (POSITIVE TESTING) ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(gmoPage);
		log.debug("Navigating to : " + gmoPage);
	}

	@AfterClass
	public void shutDown() {
		driver.quit();

	}

	@AfterMethod
	public void afterMethod() {
		// Seperator Line for Logger Tracking
		log.debug("==============================================================================================");
	}

	@Test(description = " VERIFY GMO SITE EXPECTED/ACTUAL URL & PAGE TITLE ")
	public void verifyEnterGmoSite() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY GMO SITE URL & PAGE TITLE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Expected and Actual Url Verification
			String expectedUrl = "http://demo.borland.com/gmopost/online-catalog.htm";
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				log.debug("Current URL is the Expected URL - PASSED");
			} else {
				log.debug("Current URL IS NOT the Expected URL - FAILED");
			}

			// Expected and Actual Page Title Verification
			String expectedTitle = "OnLine Catalog";
			String actualTitle = driver.getTitle();
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				log.debug("Current Title is the Expected TITLE - PASSED");
			} else {
				log.debug("Current Title IS NOT the Expected TITLE - FAILED");
			}

		} catch (Exception e) {
			log.debug("(GMO HOMEPAGE SITE) Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail1.jpg"));
		}
	}

	@Test(description = " GET ALL ROWS DATA ", dependsOnMethods = { "verifyEnterGmoSite" })
	public void getTableRow() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - PRINT ALL DATA FROM ITEMS TABLE ~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving row element
			List<WebElement> row = driver
					.findElements(By.xpath("html/body/form/table/tbody/tr[2]/td/div/center/table/tbody/tr[*]"));

			// Printing row count
			log.debug("Row count = : " + row.size());

			// Priting all Data in table with for loop
			for (int a = 0; a < row.size(); a++) {
				log.debug(row.get(a).getText());
			}

		} catch (Exception e) {
			log.debug("Table Row Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail2.jpg"));

		}
	}

	@Test(description = "VERIFY GMO POST LOGO", dependsOnMethods = { "getTableRow" })
	public void verifyGmoPostLogo() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY GMO POST LOGO IS VISIBLE ~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Saving Gmo Post Logo as Webelement
			WebElement logo = driver.findElement(By.xpath("html/body/table/tbody/tr/td[2]/h3/img"));

			// Checking if Gmo Logo is Displayed
			if (logo.isDisplayed()) {
				log.debug("Gmo Post Logo Visible : PASSED");
			} else {
				log.debug("Gmo Post Logo NOT Visible : FAILED");
			}

		} catch (Exception e) {
			log.debug("Gmo Post Logo Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail3.jpg"));
		}
	}

	@Test(description = "VERIFY RESET FORM BUTTON ", dependsOnMethods = { "verifyGmoPostLogo" })
	public void verifyResetFormButton() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~~` GMO PAGE TEST - VERIFY RESET FORM BUTTON IS DISPLAYED AND ENABLED ~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving Reset Form Button as Webelement
			WebElement resetFormButton = driver.findElement(By.name("bReset"));

			// Checking if Reset Form button is Displayed and Enabled
			if (resetFormButton.isDisplayed() && resetFormButton.isEnabled()) {
				log.debug("Reset Form Button Enabled : PASS");
			} else {
				log.debug("Reset Form Button Disabled : FAIL");
			}

		} catch (Exception e) {
			log.debug("Reset Form Button Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail4.jpg"));
		}
	}

	@Test(description = "VERIFY PLACE AN ORDER BUTTON", dependsOnMethods = { "verifyResetFormButton" })
	public void verifyPlaceOrderButton() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY PLACE AN ORDER BUTTON IS DISPLAYED AND ENABLED ~~~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving Place Order Button as Webelement
			WebElement placeOrderButton = driver.findElement(By.name("bSubmit"));

			// Checking if Place Order Button is Displayed and Enabled
			if (placeOrderButton.isDisplayed() && placeOrderButton.isEnabled()) {
				log.debug("Place Order Button Enabled : PASSED");
			} else {
				log.debug("Place Order Button Disabled : FAILED");
			}

		} catch (Exception e) {
			log.debug("Place Order Button Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail5.jpg"));
		}
	}

	@Test(description = "VERIFY ENTERING QTY IN ORDER TABLE", dependsOnMethods = { "verifyPlaceOrderButton" })
	public void startOrder() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY USER CAN ENTER QTY IN ALL ITEMS ~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving Items as Webelements
			WebElement tents = driver.findElement(By.name("QTY_TENTS"));
			WebElement backpacks = driver.findElement(By.name("QTY_BACKPACKS"));
			WebElement glasses = driver.findElement(By.name("QTY_GLASSES"));
			WebElement socks = driver.findElement(By.name("QTY_SOCKS"));
			WebElement boots = driver.findElement(By.name("QTY_BOOTS"));
			WebElement shorts = driver.findElement(By.name("QTY_SHORTS"));
			WebElement placeOrderButton = driver.findElement(By.name("bSubmit"));

			// Entering # of Qty for Each Item
			tents.clear();
			tents.sendKeys("2");
			log.debug("Tents Qty : 2");
			backpacks.clear();
			backpacks.sendKeys("4");
			log.debug("Backpacks Qty : 4");
			glasses.clear();
			glasses.sendKeys("6");
			log.debug("Glasses Qty : 6");
			socks.clear();
			socks.sendKeys("10");
			log.debug("Socks Qty : 10");
			boots.clear();
			boots.sendKeys("1");
			log.debug("Boots Qty : 1");
			shorts.clear();
			shorts.sendKeys("8");
			log.debug("Shorts Qty : 8");

			// Clicking Place Order Button
			placeOrderButton.click();

			// Verify Expected and Actual Url
			String expectedUrl = "http://demo.borland.com/gmopost/cgi-bin/perl.exe?place-order.pl";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
			log.debug("EXPECTED URL AND ACTUAL URL - PASSED");

			// Verify Expected and Actual Page Title
			String expectedTitle = "Place Order";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			log.debug("EXPECTED PAGE TITLE AND ACTUAL PAGE TITLE - PASSED");

		} catch (Exception e) {
			log.debug("Place Order Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail6.jpg"));
		}
	}

	@Test(description = " VERIFY DATA IN ORDER TABLE ", dependsOnMethods = { "startOrder" })
	public void printOrderTable() throws IOException {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY ALL DATA IN ORDER TABLE ~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving order table as List
			List<WebElement> orderRows = driver
					.findElements(By.xpath("html/body/form/table/tbody/tr[1]/td/div/center/table/tbody/tr"));

			// Printing row count
			log.debug("Row count : " + orderRows.size());

			// Printing all data in order table using For Loop
			for (int a = 0; a < orderRows.size(); a++) {
				log.debug(orderRows.get(a).getText());
			}

		} catch (Exception e) {
			log.debug("Order Table Data Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail7.jpg"));
		}
	}

	@Test(description = " VERIFY PROCEED WITH ORDER BUTTON THEN CLICK THE BUTTON ", dependsOnMethods = {
			"printOrderTable" })
	public void verifyProceedButton() throws IOException {
		log.debug(
				"~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY PROCEED WITH ORDER BUTTON IS DISPLAYED & ENABLED, THEN CLICK THE BUTTON ~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving Proceed with Order Button as Webelement
			WebElement proceedButton = driver.findElement(By.name("bSubmit"));

			// Verify Proceed with Order Button is displayed and enabled
			if (proceedButton.isDisplayed() && proceedButton.isEnabled()) {
				log.debug(" Proceed With Order Button is Displayed & Enabled ");

				// If enabled click the button
				proceedButton.click();

			} else {
				log.debug(" Proceed With Order Button IS NOT DISPLAYED & ENABLED ");
			}
		} catch (Exception e) {
			log.debug(" Proceed Order Button Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail8.jpg"));
		}
	}

	@Test(description = " VERIFY BILLING INFO PAGE URL & PAGE TITLE ", dependsOnMethods = { "verifyProceedButton" })
	public void verifyBillingInfoPage() throws IOException {

		log.debug(
				"~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY BILLING INFORMATION PAGE EXPECTED/ACTUAL URL & PAGE TITLE ~~~~~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Verify Expected and Actual Url
			String expectedUrl = "http://demo.borland.com/gmopost/cgi-bin/perl.exe?ship-info.pl";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
			log.debug(" BILLING INFO PAGE EXPECTED & ACTUAL URL - PASSED ");

			// Verify Expected and Actual Page Title
			String expectedTitle = "Billing Information";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			log.debug(" BILLING INFO PAGE EXPECTED & ACTUAL PAGE TITLE - PASSED ");

		} catch (Exception e) {
			log.debug("Billing Info Page Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail9.jpg"));
		}
	}

	@Test(description = " ENTER ALL BILLING INFORMATION USING (PROPERTIES) ", dependsOnMethods = {
			"verifyBillingInfoPage" })
	public void enterBillingInfo() throws IOException {

		log.debug("~~~~~~~~~~~~~~~~~ GMO PAGE TEST - ENTER ALL INFO INTO THE FORM USING (PROPERTIES FILES) ~~~~~~~~~~~~~~~~~");

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Saving all Input fields as WebElements
			WebElement billName = driver.findElement(By.name("billName"));
			WebElement billAddress = driver.findElement(By.name("billAddress"));
			WebElement billCity = driver.findElement(By.name("billCity"));
			WebElement billState = driver.findElement(By.name("billState"));
			WebElement billZip = driver.findElement(By.name("billZipCode"));
			WebElement billPhone = driver.findElement(By.name("billPhone"));
			WebElement billEmail = driver.findElement(By.name("billEmail"));
			WebElement creditCardsList = driver.findElement(By.name("CardType"));
			WebElement cardNumber = driver.findElement(By.name("CardNumber"));
			WebElement cardExpDate = driver.findElement(By.name("CardDate"));
			WebElement shipToBillAddress = driver.findElement(By.name("shipSameAsBill"));
			WebElement proceedFinalOrder = driver.findElement(By.name("bSubmit"));

			// Initializing properties file for Inputting data
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\tests\\positive\\form_info.properties");
			prop.load(fis);

			// Entering data in designated fields
			billName.clear();
			billName.sendKeys(prop.getProperty("billName"));
			billAddress.clear();
			billAddress.sendKeys(prop.getProperty("billAddress"));
			billCity.clear();
			billCity.sendKeys(prop.getProperty("billCity"));
			billState.clear();
			billState.sendKeys(prop.getProperty("billState"));
			billZip.clear();
			billZip.sendKeys(prop.getProperty("billZip"));
			billPhone.clear();
			billPhone.sendKeys(prop.getProperty("billPhone"));
			billEmail.clear();
			billEmail.sendKeys(prop.getProperty("billEmail"));

			// Saving as Select for credit card list
			Select cards = new Select(creditCardsList);
			cards.selectByVisibleText(prop.getProperty("cardType"));

			// Enter credit card info
			cardNumber.sendKeys(prop.getProperty("cardNumber"));
			cardExpDate.sendKeys(prop.getProperty("cardExpDate"));

			log.debug(" ALL FORMS HAVE BEEN FILLED WITH VALID DATA ");

			// Clicking ship to bill address
			shipToBillAddress.click();

			// Verify proceed with order Button is displayed and enabled
			if (proceedFinalOrder.isDisplayed() && proceedFinalOrder.isEnabled()) {
				log.debug(" Proceed With Order Button is Displayed & Enabled ");

				// If enabled click the Button
				proceedFinalOrder.click();

			} else {
				log.debug(" Proceed With Order Button IS NOT DISPLAYED & ENABLED ");
			}

		} catch (Exception e) {
			log.debug(" Proceed Ordering Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail10.jpg"));
		}
	}

	@Test(description = " VERIFY ORDER RECEIPT DATA FROM TABLES ", dependsOnMethods = { "enterBillingInfo" })
	public void verifyOrderReceiptData() throws IOException {

		log.debug("~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY ALL DATA FROM THE ORDER RECEIPT ~~~~~~~~~~~~~~~~~");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Verify Expected and Actual Url
			String expectedUrl = "http://demo.borland.com/gmopost/cgi-bin/perl.exe?confirm-order.pl";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
			log.debug(" Order Receipt Expected & Actual URL - PASSED ");

			// Verify Expected and Actual Page Title
			String expectedTitle = "OnLine Store Receipt";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			log.debug(" Order Receipt Expected & Actual PAGE TITLE - PASSED ");

		} catch (Exception e) {
			log.debug(" Url and Page Title Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail11.jpg"));
		}

		try {
			List<WebElement> shipAndBill = driver
					.findElements(By.xpath("html/body/table[2]/tbody/tr[1]/td/div/center/table/tbody/tr"));
			log.debug(" Shipping and Billing Table Info Row Count :" + shipAndBill.size());
			log.debug(" Printing Shipping and Billing Info Data in Table ");
			for (int a = 0; a < shipAndBill.size(); a++) {
				log.debug(shipAndBill.get(a).getText());
			}
		} catch (Exception e) {
			log.debug(" Shipping and Billing Data Table Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail12.jpg"));
		}

		try {
			List<WebElement> orderTotalTable = driver.findElements(
					By.xpath("html/body/table[2]/tbody/tr[2]/td/table/tbody/tr/td/div/center/table/tbody/tr"));
			log.debug(" Order Receipt Items Table Info Row Count :" + orderTotalTable.size());
			log.debug(" Printing Order Receipt Info Data in Table ");
			for (int a = 0; a < orderTotalTable.size(); a++) {
				log.debug(orderTotalTable.get(a).getText());
			}
		} catch (Exception e) {
			log.debug(" Printing Order Receipt Data in Table Exception : " + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail13.jpg"));
		}
	}

	@Test(description = "VERIFY RETURN TO HOMEPAGE BUTTON", dependsOnMethods = { "verifyOrderReceiptData" })
	public void returnToHomepage() throws IOException {
		log.debug(" ~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TEST - VERIFY ALL DATA FROM THE ORDER RECEIPT .... ~~~~~~~~~~~~~~~~~~~~~ ");
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement returnHomepage = driver.findElement(By.name("bSubmit"));
			if (returnHomepage.isDisplayed() && returnHomepage.isEnabled()) {
				log.debug(" Return to Homepage Button is Displayed & Enabled ");
				returnHomepage.click();
				log.debug(" Clicked on Return to Homepage ");
			}
		} catch (Exception e) {
			log.debug(" Return to Homepage Button Exception :" + e);
			File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Screenshot, new File(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\screenshots\\Fail14.jpg"));
		}
	}
}

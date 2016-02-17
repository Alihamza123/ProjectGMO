package framework.tests.negative;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmoPage {

	WebDriver driver;
	String gmoPage = "http://demo.borland.com/gmopost/";
	Logger log = Logger.getLogger("framework");

	@BeforeClass
	public void startUp() {
		log.debug(
				"~~~~~~~~~~~~~~~~~~~~~ GMO PAGE TESTS NOW STARTING ( NEGATIVE TESTING ) ~~~~~~~~~~~~~~~~~~~~~");

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

	@Test(description = " ENTER GMO PAGE AND START THE ORDER", alwaysRun = true)
	public void enterGmoPage() {

		log.debug(" ~~~~~~~~~~~~~~~~~~~~~ Starting Entering Gmo Webpage Tests .... ~~~~~~~~~~~~~~~~~~~~~ ");

		try {
			// Adding Explicit Wait for Enter Gmo Button
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement enterButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("bSubmit")));

			// Clicking Button
			enterButton.click();

			// Print Current URL and PAGE TITLE
			log.debug("Current website ... " + driver.getCurrentUrl());
			log.debug("Current website Page title .... " + driver.getTitle());

			// Adding Implicit Wait for Items to load
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Save Items as Webelements
			WebElement domeTents = driver.findElement(By.name("QTY_TENTS"));
			WebElement backpacks = driver.findElement(By.name("QTY_BACKPACKS"));
			WebElement glasses = driver.findElement(By.name("QTY_GLASSES"));
			WebElement socks = driver.findElement(By.name("QTY_SOCKS"));
			WebElement boots = driver.findElement(By.name("QTY_BOOTS"));
			WebElement shorts = driver.findElement(By.name("QTY_SHORTS"));
			WebElement placeOrder = driver.findElement(By.name("bSubmit"));

			// Enter Items # of Qty
			domeTents.clear();
			domeTents.sendKeys("2");
			backpacks.clear();
			backpacks.sendKeys("4");
			glasses.clear();
			glasses.sendKeys("2");
			socks.clear();
			socks.sendKeys("2");
			boots.clear();
			boots.sendKeys("2");
			shorts.clear();
			boots.sendKeys("1");

			// Click Place Order Button
			placeOrder.click();

			// Add Explicit Wait for PLACE ORDER Table
			WebDriverWait tableWait = new WebDriverWait(driver, 10);
			List<WebElement> row = tableWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("html/body/form/table/tbody/tr[1]/td/div/center/table/tbody/tr")));

			// Print PLACE ORDER Table Row Count
			log.debug("Row Count ... " + row.size());

			// Print All PLACE ORDER TABLE Data
			for (int a = 0; a < row.size(); a++) {
				log.debug(row.get(a).getText());
			}

			// Add Explicit wait for PROCEED ORDER Button
			WebDriverWait proceedWait = new WebDriverWait(driver, 10);
			WebElement proceedButton = proceedWait.until(ExpectedConditions.elementToBeClickable(By.name("bSubmit")));
			// Cick Proceed Order Button
			proceedButton.click();
			
		} catch (Exception e) {
			log.debug("Starting Negative Testing Exception :" + e);
		}

	}

	@Test(description = " NEGATIVE TEST - ENTER PARTIAL DATA ", dependsOnMethods = { "enterGmoPage" }, alwaysRun = true)
	public void enterInvalidData() throws IOException {

		log.debug(" ~~~~~~~~~~~~~~~~~~~~~ Starting Entering Negative Information Tests .... ~~~~~~~~~~~~~~~~~~~~~ ");

		try {
			// Declare New Properties File for Input Data
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Ali Hamza\\Desktop\\EclipseWorkspace\\Group Project Framework\\src\\framework\\tests\\negative\\form_info.properties");
			prop.load(fis);

			// Add Implicit Wait to find All Elements
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement billName = driver.findElement(By.name("billName"));
			WebElement billAddress = driver.findElement(By.name("billAddress"));
			WebElement billCity = driver.findElement(By.name("billCity"));
			WebElement shipToBillAddress = driver.findElement(By.name("shipSameAsBill"));
			WebElement placeOrder = driver.findElement(By.name("bSubmit"));

			// Input Data Through Properties.file
			billName.sendKeys(prop.getProperty("billName"));
			billAddress.sendKeys(prop.getProperty("billAddress"));
			billCity.sendKeys(prop.getProperty("BillCity"));

			// Checkbox for Ship To Same As Billing Address
			shipToBillAddress.click();
			// Click Place Order Button
			placeOrder.click();

			// Handle Alert , Get Text , Accept Alert
			Alert promptAlert = driver.switchTo().alert();
			String alertText = promptAlert.getText();
			log.debug("Alert text ... " + alertText);
			promptAlert.accept();
		} catch (Exception e) {
			log.debug("Invalid Data Negative Testing Exception :" +e );	
		}

	}
}

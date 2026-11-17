package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.ObjectInputFilter.Config;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import base.BaseTest;
import listeners.TestListener;
import pages.HomePage;
import pages.RegisterPage;
import pages.WelcomePage;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.LoggerUtility;


public class Register_A_Customer_Test extends BaseTest {
	HomePage homePage;
	RegisterPage registerPage;
	WelcomePage welcomePage;
	ExcelUtils excelUtils = new ExcelUtils("CustomerDetails.xlsx", "Register");
	 private static final org.apache.logging.log4j.Logger log =LoggerUtility.getLogger();

	@BeforeClass
	public void setUp(ITestContext context) {
		initializeWebDriver(context);
	}

	@Test(priority = 1)
	public void validate_Logo_Display() throws InterruptedException {
		// ReportUtils.createTest("Validate Parabank Logo Display");
		log.info("Running Test 1.....");
		homePage = new HomePage();
		AssertJUnit.assertTrue(homePage.urlLaunched());
		homePage.openRegisterPage();
		log.info("Home Page Opened");
		log.info("First Test Finished.");
	}

	@Test(priority = 2)
	public void register_A_Customer() throws InterruptedException {
		// ReportUtils.createTest("Register a Customer");
		log.info("Running 2 test case....");
		String expectedWelcomeMsgString = ConfigReader.getProperty("welcomeMsg");
		registerPage = new RegisterPage();
		List<String> rowList = new ArrayList<String>();
		String actualString = registerPage.signUpHeading();
		String expectedString = "Signing up is easy!";
		Assert.assertEquals(expectedString, actualString);
		for (int i = 1; i < excelUtils.getRowCount(); i++) {
			for (int j = 0; j < excelUtils.getColumnCount(i); j++)
				rowList.add(excelUtils.getCellData(i, j));
		}
		Thread.sleep(1000);
		registerPage.enterCustomerDetails(rowList);
		welcomePage = registerPage.registerCustomer();
		String actualWelcomeString = welcomePage.getWelcomeUserName();
		Assert.assertEquals(actualWelcomeString,expectedWelcomeMsgString);
		log.info("Test finished.");
	}

	@AfterClass
	public void tearDown() {
		super.tearDown();
	}
}

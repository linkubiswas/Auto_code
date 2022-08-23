package automation_test.mortgage_calculator;

import command_providers.ActOn;
import data.DataStore;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Home;
import utilities.ReadConfigFiles;
import utilities.ScreenCapture;


public class CalculateRealAprRate {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRealAprRate.class);
    WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("----------Test Name: Calculate Real Apr----------");

        String browserUrl = ReadConfigFiles.getPropertyValues("URL");
        ActOn.browser(driver).openBrowser(browserUrl);
    }
    @Test(dataProvider = "RealAprRates", dataProviderClass = DataStore.class)
    public void calculateRealApr(String homePrice, String downPayment, String interestRate, String expectedApr){

        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice(homePrice)
                .selectDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickingOnCalculateButton()
                .validateRealAprRate(expectedApr);
    }

    @AfterMethod
    public void closeBrowser(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenCapture.getScreenShot(driver);
        }
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("------------End Test Case: Calculate Real Apr----------");
    }
}

package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    // It will not allow to create an object from the class
    private DriverFactory() {
        //Do nothing
    }

    //Creating an instance object from this class
    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }
    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    });
    public WebDriver getDriver(){
        return driver.get();
    }
    public void removeDriver(){
        driver.get().quit();
        driver.remove();

    }
}

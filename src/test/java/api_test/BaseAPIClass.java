package api_test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseAPIClass {
    public Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    String testCaseName = String.format("------Test: %s--------" ,this.getClass().getName());
    String endTestCase = String.format("--------Test End: %s------" ,this.getClass().getName());

    @BeforeClass
    public void addThread(){
        ThreadContext.put("threadName",this.getClass().getName());
    }
    @BeforeMethod
    public void openBrowser(){
        LOGGER.info(testCaseName);
    }
    @AfterMethod
    public void closeBrowser(){
        LOGGER.info(endTestCase);
    }
}

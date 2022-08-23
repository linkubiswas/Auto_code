package playGround;

import data.DataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestNgMavenExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(TestNgMavenExampleTest.class);

    @Test(dataProvider = "MultipleColumnValues", dataProviderClass = DataStore.class)
    public void run(String name, String state, int age)  {
        LOGGER.info("Name is: " + name);
        LOGGER.info("State is: " + state);
        LOGGER.info("Age is: " + age);
    }
}

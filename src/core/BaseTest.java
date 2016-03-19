package core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by ars on 3/18/16.
 */
public class BaseTest {

    @BeforeMethod
    public void init(){
        Driver.init();
    }

    @AfterMethod
    public void cleanup() {
        Driver.tearDown();
    }

}

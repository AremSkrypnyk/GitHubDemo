package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ars on 3/17/16.
 */

public class Driver {

    private static final String baseUrl = "https://github.com";

    private static WebDriver driver;

    public static WebDriver get() {
        return driver;
    }

    public static void setDriver(WebDriver driverInput) {
        driver = driverInput;
    }

    public static void init(){
        WebDriver chromeDriver;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-web-security");
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        option.setExperimentalOption("prefs", prefs);

        chromeDriver = new ChromeDriver(option);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(
                10,
                TimeUnit.SECONDS
        );

        chromeDriver.get(baseUrl);

        setDriver(chromeDriver);
    }

    public static boolean isInitialized() {
        return driver != null;
    }

    public static void pause(int timeout){
        try {
            Thread.sleep(timeout);
        }
        catch (InterruptedException e){ }
    }

    public static WebElement findElement(By by){
        List<WebElement> elements;
        Assert.assertNotNull(by, "Cannot find element: locator in null");
        Assert.assertNotNull(get(), "Driver is not initialized");
        elements = get().findElements(by);
        if (elements.isEmpty())
            throw new Error("No element was found.");
        if (elements.size() > 1)
            throw new Error("More than 1 element for found using giving locator.");
        return elements.get(0);
    }

    public static List<WebElement> findAll(By by) {
        Assert.assertNotNull(by, "Cannot find elements: the selector is null.");
        Assert.assertNotNull(get(), "Driver appears to be not initialized or destructed at some moment.");
        List<WebElement> result;
        try {
            result = get().findElements(by);
        } catch (WebDriverException e) {
            result = Collections.emptyList();
        }
        return result;
    }

    public static void tearDown() {
        if (Driver.isInitialized())
            Driver.get().quit();
        else
            throw new Error("Driver was not initialized and cannot be terminated.");
    }

}

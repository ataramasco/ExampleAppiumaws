package Tests.AbstractBaseTests;

import Pages.HomePage;
import Pages.NavigationPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * An abstract base for all of the Android tests within this package
 *
 * Responsible for setting up the Appium test Driver
 */
public abstract class TestBase {
    /**
     * Make the driver static. This allows it to be created only once
     * and used across all of the test classes.
     */
    public static AndroidDriver driver;

    /**
     * This allows the navigation to work within the app.
     * The category name is returned so we can navigate to it from the navigation
     * drawer.
     *
     * @return The name of the Android category
     */
    public abstract String getName();

    /**
     * A page containing the navigation drawer
     */
    private NavigationPage navigationPage;
    private HomePage homePage;

    /**
     * Method to initialize the test's page
     */
    @BeforeTest
    public abstract void setUpPage();

    /**
     * This method runs before any other method.
     *
     * Appium follows a client - server model:
     * We are setting up our appium client in order to connect to Device Farm's appium server.
     *
     * We do not need to and SHOULD NOT set our own DesiredCapabilities
     * Device Farm creates custom settings at the server level. Setting your own DesiredCapabilities
     * will result in unexpected results and failures.
     *
     * @throws MalformedURLException An exception that occurs when the URL is wrong
     */
    @BeforeSuite
    public void setUpAppium() throws MalformedURLException{

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";

        URL url = new URL(URL_STRING);

        //DesiredCapabilities appium object
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", Platform.ANDROID);
        capabilities.setCapability("BROWSER_NAME", "Chrome");

        driver = new AndroidDriver(url, capabilities);

        //Use a higher value if your mobile elements take time to show up
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
    }

    /**
     * Always remember to quit
     */
    @AfterSuite
    public void tearDownAppium(){
        driver.quit();
    }

    /**
     *
     *  Creates a navigation page and navigates to the Class' category
     *  within the navigation drawer
     *
     */
    @BeforeClass
    public void navigateTo() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.navigateLogin();
    }

    /**
     * Restart the app after every test class to go back to the main
     * screen and to reset the behavior
     */
    @AfterClass
    public void restartApp() {
        driver.resetApp();
    }
}
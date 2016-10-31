

package Tests;

import Pages.HomePage;
import Tests.AbstractBaseTests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Tests for jobaline.com homepage
 */
public class HomePageTest extends TestBase {
    private final String HOMEPAGE_HEADLINE = "existing";
    private final String HOMEPAGE_SUBHEADER = "users";

    private HomePage homePageTest;

    /**
     * Sets up homepage
     */
    @BeforeTest
    @Override
    public void setUpPage(){
        homePageTest = new HomePage(driver);
    }

    /**
     * Asserts the homepage headline
     */
    @Test
    public void testHomePageHeadline() {
        Assert.assertEquals(homePageTest.getHeadlineValue(), HOMEPAGE_HEADLINE);

    }

    /**
     * Asserts the homepage subheader
     */
    //@Test
    //public void testHomePageSubheader() {
    //    Assert.assertEquals(homePageTest.getSubheaderValue(), HOMEPAGE_SUBHEADER);
    //}

    @Override
    public String getName() {
        return "Home";
    }
}

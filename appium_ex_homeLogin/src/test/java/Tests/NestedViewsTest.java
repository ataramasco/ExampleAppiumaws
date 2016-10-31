
package Tests;

import Pages.NestedViewsPage;
import Tests.AbstractBaseTests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Tests for nested views
 */
public class NestedViewsTest extends TestBase {
    private NestedViewsPage nestedViewsPage;
    private final String UP_MESSAGE_1 = "Press to go to the next level";
    private final String UP_MESSAGE_2 = "Final Level";

    private final int NUMBER_NEXT_BACK_BUTTON = 8;
    private final int NUMBER_BACK_BUTTON = 6;
    private final String NEXT_BACK_BUTTON_COUNTER_BEFORE = "9";
    private final String NEXT_BACK_BUTTON_COUNTER_AFTER = "3";

    @Override
    public String getName() {
        return "Nested Views";
    }

    @BeforeTest
    @Override
    public void setUpPage() {
        nestedViewsPage = new NestedViewsPage(driver);
    }

    @Test
    public void testBackButton(){
        nestedViewsPage.pressBackButtonCategory();

        for (int i = 0; i < NUMBER_NEXT_BACK_BUTTON; i++) {
            nestedViewsPage.pressNextBackButton();
        }

        Assert.assertEquals(nestedViewsPage.getCounter(), NEXT_BACK_BUTTON_COUNTER_BEFORE);

        for (int i = 0; i < NUMBER_BACK_BUTTON; i++) {
            nestedViewsPage.pressBackButton();
        }

        Assert.assertEquals(nestedViewsPage.getCounter(), NEXT_BACK_BUTTON_COUNTER_AFTER);
    }

    /**
     * Tests the up button by going up
     */
    @Test
    public void testUpButton(){
        nestedViewsPage.pressUpButtonCategory();

        Assert.assertEquals(nestedViewsPage.getUpMessage(), UP_MESSAGE_1);

        nestedViewsPage.pressNextUpButton();

        Assert.assertEquals(nestedViewsPage.getUpMessage(), UP_MESSAGE_2);

        nestedViewsPage.navigateUp();

        Assert.assertEquals(nestedViewsPage.getUpMessage(), UP_MESSAGE_1);
    }

    /**
     * Navigate up to reset the test
     */
    @AfterMethod
    public void goBackToMenu(){
        nestedViewsPage.navigateUp();
    }
}

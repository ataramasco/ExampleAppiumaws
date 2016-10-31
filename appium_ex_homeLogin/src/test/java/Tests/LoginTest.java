

package Tests;

import Pages.LoginPage;
import Tests.AbstractBaseTests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Tests for a login page
 */
public class LoginTest extends TestBase {
    private final String LOGIN_SUCCESS_MESSAGE = "You are logged on as admin";
    private final String LOGIN_FAIL_MESSAGE = "Incorrect email or password. Please try again or sign-up";
    private final String CORRECT_USER_NAME = "admin";
    private final String CORRECT_PASSWORD = "password";
    private final String FAIL_USER_NAME = "Wrong User";
    private final String FAIL_PASSWORD = "12345";

    private LoginPage loginPage;

    @Override
    public String getName() {
        return "Login Page";
    }

    /**
     * Creates a login
     */
    @BeforeTest
    @Override
    public void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    /**
     * Tests logging in with valid credentials by verifying if the login message is correct
     */
    @Test
    public void loginSuccessFully(){
        loginPage.loginIn(CORRECT_USER_NAME, CORRECT_PASSWORD);
        Assert.assertEquals(loginPage.getMessage(), LOGIN_SUCCESS_MESSAGE);
    }

    /**
     * Tests logging in with invalid credentials by verifying if the error message is correct
     */
    @Test
    public void loginFail() {
        loginPage.loginIn(FAIL_USER_NAME, FAIL_PASSWORD);
        Assert.assertEquals(loginPage.getMessage(), LOGIN_FAIL_MESSAGE);
    }

    /**
     * After each test method, logout or try again

    @AfterMethod
    public void logOut(){
        loginPage.pressAltButton();
        Assert.assertTrue(loginPage.checkIfBackAtLogin());
    }
     */
}

/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * A login page
 */
public class LoginPage extends BasePage {
    /**
     * The login button
     */
    @FindBy(name= "btnEmailLogin")
    private WebElement btnEmailLogin;

    //@FindBy(linkText = "Employer")
    //private WebElement employersButton;

    @Override protected String getPath()
    {
        return "/";
    }

    /**
     * The user name input
     */

    @FindBy(name = "txtEmail")
    private WebElement txtEmail;

    /**
     * The password input
     */
    @FindBy(name = "txtPwd")
    private WebElement txtPwd;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Tries to login with a set of credentials
     *
     * @param userName the username
     * @param password the password
     */
    public void loginIn(String userName, String password){
        txtEmail.sendKeys(userName);
        txtPwd.sendKeys(password);
        btnEmailLogin.click();
    }

    //public void accessLogin(){
    //    employersButton.click();
    //}

    /**
     *
     * @return the login message
     */
    public String getMessage(){
        return driver.findElementById("errMsgText").getText();
    }

    /**
     * Checks to see if back at login page
     *
     * @return is back at login
     */
    public boolean checkIfBackAtLogin(){
        return btnEmailLogin.isDisplayed() && txtEmail.isDisplayed() && txtPwd.isDisplayed();
    }

    /**
     * Presses the logout/try again button
     */
    //public void pressAltButton(){
    //    driver.findElementById("alt_button").click();
    //}
}

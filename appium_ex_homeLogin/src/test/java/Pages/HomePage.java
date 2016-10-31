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
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A page representing a static homepage
 */
public class HomePage extends BasePage{


    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public static void open()
    {
		/*
		* ------- Tutorial comments --------
		*
		* In general, the default implementation for this method would be:
		*
		* BasePage.open();
		*
		* But our system has "jobs" as the default subdomain so we need to specify the "www" domain.
		*
		* ------- End tutorial comments --------
		* */

        BasePage.open("www");
    }

    @Override protected String getPath()
    {
        return "/";
    }

    /**
     * The headline of the homepage
     */
    @FindBy(className = "htext")
    private WebElement headline;

    @FindBy(className = "login")
    private WebElement loginLink;

    /**
     * the subheader of the homepage
     */
    @FindBy(className = "last")
    private WebElement employerLink;

    @FindBy(className = "first")
    private WebElement jobseekLink;


    public void navigateLogin(){
        loginLink.click();
        employerLink.click();
    }

    /**
     *
     * @return the header text content
    */
    public String getHeadlineValue() {
        return headline.getText();
    }

    /**
     *
     * @return the subheader text content

    public String getSubheaderValue() {
        return subheader.getText();
    }*/
}

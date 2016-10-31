
package Pages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * A base for all the pages within the suite
 */
public abstract class BasePage{

    /**
     * The driver
     */
    protected final AppiumDriver driver;

    public static void open()
    {
        createSubclassInstance().openImpl();
    }


    public static void open(String subdomain)
    {
        createSubclassInstance().openImpl(subdomain);
    }

    private static BasePage createSubclassInstance()
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String subClassName = null;

        // 0 will be java.lang.Thread
        // Then, it will be several times BasePage until we reach teh subclass
        for(int i = 1; i < stackTrace.length; i++)
        {
            StackTraceElement stackTraceElement = stackTrace[i];
            if(!stackTraceElement.getClassName().equals(BasePage.class.getName()))
            {
                subClassName = stackTraceElement.getClassName();
                break;
            }
        }

        Class<? extends BasePage> pageClass;
        try
        {
            pageClass = (Class<? extends BasePage>)Class.forName(subClassName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Could not create an instance of the page object. This is a programming error. Please fix it.");
        }

        // If the class obtained from the stacktrace is not a subclass of BasePage is because the static method invoked was not defined in the
        // subclass.
        if(!BasePage.class.isAssignableFrom(pageClass))
        {
            throw new RuntimeException("You need to define the static method in the subclass.");
        }

        BasePage page;
        try
        {
            page = pageClass.newInstance();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Could not create an instance of the page object. This is a programming error. Please fix it.");
        }

        return page;
    }

    protected abstract String getPath();

    protected void verifyLocationImpl()
    {
        //verifyLocationByCheckingUrlContainsText(EnvironmentUtils.getApplicationUnderTestDomain() + getPath());
    }


    protected final void openImpl()
    {
        openImpl("jobs");
    }


    protected final void openImpl(String subdomain)
    {
        openImpl(subdomain, new Object[]{});
    }


    protected final void openImpl(String subdomain, Object... parameters)
    {
        //getSeleniumClient().openUrl(getUrl(subdomain, parameters));
    }


    protected final String getUrlImpl()
    {
        return getUrlImpl("jobs");
    }


    protected final String getUrlImpl(String subdomain)
    {
        return getUrlImpl(subdomain, new Object[]{});
    }


    protected final String getUrlImpl(String subdomain, Object... parameters)
    {
        if(parameters.length % 2 != 0)
        {
            throw new RuntimeException("The list of arguments to create the url query string must be pair since it will be mapped to a parameter name/value map.");
        }

        String queryString = "";

        if(parameters.length != 0)
        {
            queryString = "?";
            for(int i = 0; i < parameters.length; i += 2)
            {
                queryString += String.format("%s=%s&", parameters[i], parameters[i + 1]);
            }
        }

        return "http://" + subdomain + /*EnvironmentUtils.getApplicationUnderTestDomain() +*/ getPath() + queryString;
    }



    /**
     * A base constructor that sets the page's driver
     *
     * The page structure is being used within this test in order to separate the
     * page actions from the tests.
     *
     * Please use the AppiumFieldDecorator class within the page factory. This way annotations
     * like @AndroidFindBy within the page objects.
     *
     * @param driver the appium driver created in the beforesuite method.
     */
    protected BasePage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
    }


}
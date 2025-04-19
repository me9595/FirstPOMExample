package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.util.ElementUtil;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    // 1. locators
    private By emailId = By.xpath("//input[@name='email']");
    private By password = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By ForgottenPasswordLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
    private By registerLink = By.xpath("(//a[text()='Register'])[2]");

    // 2. page constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    // 3. page actions
    @Step("Waiting for Login page title...and get the login page title...")
    public String getPageTitle(){
        String title = elementUtil.waitForTitleIs(AppConstants.Default_Time_Out, AppConstants.Login_Page_Title);
        LOG.info("Login page title is :" +title);
        return title;
    }

    @Step("Get Login page URL...")
    public String getLoginPageURL(){
        String url = elementUtil.waitForUrlIs(AppConstants.Default_Time_Out, AppConstants.Login_Page_URL);
        LOG.info("Login page URL is :" +url);
        return url;
    }

    @Step("Get Login page Forgottrn Password Link is present...")
    public boolean isForgottrnPasswordLinkIsPresent(){
        return elementUtil.doEleIsDisplayed(ForgottenPasswordLink);
    }

    @Step("Login with valid User name and password...")
    public AccountsPage doLogin(String ur, String pwd){
        elementUtil.doSendKeysWithWait(emailId, AppConstants.Default_LargeTime_Out, ur);
        LOG.info("Enter User Name : " +ur);
        elementUtil.doSendKeys(password, pwd);
        LOG.info("Enter Password : " +pwd);
        elementUtil.doClick(loginButton);
        LOG.info("Click on login button..");
        return new AccountsPage(driver);
    }

    @Step("Navigate to the Register page...")
    public RegisterPage nevigaterToregisterPage(){
        LOG.info("Navigate to Register page...");
        elementUtil.doClickWithWait(registerLink, AppConstants.Default_Time_Out);
        LOG.info("Click on Register Link");
        return new RegisterPage(driver);
    }
}

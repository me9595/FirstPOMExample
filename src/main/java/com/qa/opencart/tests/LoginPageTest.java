package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Description("Login page title test...")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void loginPageTitleTest(){
        String actualTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);
    }

    @Description("Login page URL test...")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void loginPageURLTest(){
        String actualURL = loginPage.getLoginPageURL();
        Assert.assertEquals(actualURL, AppConstants.Login_Page_URL);
    }

    @Description("Login page Forgotten Pawword Link Exist test...")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void forgottenPawwordLinkExistTest(){
        boolean flag = loginPage.isForgottrnPasswordLinkIsPresent();
        Assert.assertTrue(flag);
    }

    @Description("Login page Correct user name and password test...")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void loginTest(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        String actualTitle = accountsPage.getAccountPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.Account_Page_Title);
    }
}
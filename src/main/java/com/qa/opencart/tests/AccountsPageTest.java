package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountsPageTest extends BaseTest {

    @Description("Login page Correct user name and password test...")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeClass
    public void accSetUp(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password") );
    }

    @Description("Login page Title test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1)
    public void accountPageTitleTest(){
        String actualTitle = accountsPage.getAccountPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.Account_Page_Title);
    }

    @Description("Login page URL test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void accountPageURLTest(){
        String actualURL = accountsPage.getAccountPageURL();
        Assert.assertEquals(actualURL, AppConstants.Account_page_URL);
    }

    @Description("Login page Gogout Link Is Present test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void logoutLinkIsPresentTest(){
        boolean flag = accountsPage.isLogoutLinkIsPresent();
        Assert.assertTrue(true);
    }

    @Description("Login page Search Box Is Present test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4)
    public void searchBoxIsPresentTest(){
        boolean flag = accountsPage.isSearchBoxIsPresent();
        Assert.assertTrue(true);
    }


    @DataProvider
    public Object[][] getProductKey(){
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }
    @Description("Login page Search product test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "getProductKey", priority = 5)
    public void searchCheckTest(String productKey){
        searchResultPage = accountsPage.performSearch(productKey);
        Assert.assertTrue(searchResultPage.isSearchSuccessful());
    }


    @DataProvider
    public Object[][] getProductData(){
        return new Object[][]{
                {"Macbook", "MacBook Pro"},
                {"Macbook", "MacBook Air"},
                {"iMac", "iMac"},
                {"Samsung", "Samsung SyncMaster 941BW"},
                {"Samsung", "Samsung Galaxy Tab 10.1"}
        };
    }
    @Description("Login page Get product data test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "getProductData", priority = 6)
    public void searchTest(String searchKey, String mainProductSearch){
        searchResultPage = accountsPage.performSearch(searchKey);
        if (searchResultPage.isSearchSuccessful()){
            productInformationPage = searchResultPage.selectProduct(mainProductSearch);
            String actualProductHeader =  productInformationPage.getProductHeader(mainProductSearch);
            Assert.assertEquals(actualProductHeader, mainProductSearch);
        }
    }
}
package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(ProductPageTest.class);

    @Description("Login page Correct user name and password test...")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeClass
    public void productInfoSetup(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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

    @Description("Login page Product Header test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "getProductData", priority = 1)
    public void productHeaderTest(String searchKey, String mainProductSearch){
        searchResultPage = accountsPage.performSearch(searchKey);
        productInformationPage = searchResultPage.selectProduct(mainProductSearch);
        String actualProductHeader = productInformationPage.getProductHeader(mainProductSearch);
        Assert.assertEquals(actualProductHeader, mainProductSearch);
    }

    @Description("Login page Get Product Image Count test...")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "getProductData" , priority = 2)
    public void getProductImageCountTest(String searchKey, String mainProductSearch){
        searchResultPage = accountsPage.performSearch(searchKey);
        productInformationPage = searchResultPage.selectProduct(mainProductSearch);
        int actualProductImages =productInformationPage.getProductImageCount();
        System.out.println("Actual product images are : " +actualProductImages);
    }
}

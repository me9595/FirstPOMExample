package com.qa.opencart.base;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.tests.ProductPageTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class    BaseTest {

    Driverfactory df;
    public Properties prop;
    public WebDriver driver;

    public LoginPage loginPage;
    public AccountsPage accountsPage;
    public SearchResultPage searchResultPage;
    public ProductInformationPage productInformationPage;
    public RegisterPage registerPage;

    @BeforeTest
    public void setUp(){
        df = new Driverfactory();
        prop = df.initProp();
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

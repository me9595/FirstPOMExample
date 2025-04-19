package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");
    private By searchBox = By.xpath("//input[@name='search']");
    private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

    private static final Logger LOG = Logger.getLogger(AccountsPage.class);

    public AccountsPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Get the Account page title and return it...")
    public String getAccountPageTitle(){
        String title = elementUtil.waitForTitleIs(AppConstants.Default_Time_Out, AppConstants.Account_Page_Title);
        LOG.info("Accounts page title is : " +title);
        return title;
    }

    @Step("Get the Account page url and return it...")
    public String getAccountPageURL(){
        String url = elementUtil.waitForUrlIs(AppConstants.Default_Time_Out, AppConstants.Account_page_URL);
        LOG.info("Accounts page URL is : " +url);
        return url;
    }

    @Step("Get the Account page Logout Link Is Present...")
    public boolean isLogoutLinkIsPresent(){
        boolean flag = elementUtil.doEleIsDisplayed(logoutLink);
        LOG.info("Account page Logout Link Is Present...");
        return flag;
    }

    @Step("Account page Search Box Is Present...")
    public boolean isSearchBoxIsPresent(){
        boolean flag = elementUtil.doEleIsDisplayed(searchBox);
        LOG.info("Account page Search Box Is Present...");
        return  flag;
    }

    @Step("Account page Perform Search function...")
    public SearchResultPage performSearch(String productKey){
        LOG.info("Product productKey is : " +productKey);

        if(isSearchBoxIsPresent()){
            elementUtil.doSendKeys(searchBox, productKey);
            elementUtil.doClick(searchButton);
            return new SearchResultPage(driver);
        }else {
            System.out.println("Entered Product is not present.. : " +productKey);
            LOG.info("Entered Product is not present.. : " +productKey);
            return null;
        }
    }
}
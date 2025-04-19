package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SearchResultPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private static final Logger LOG = Logger.getLogger(SearchResultPage.class);

    private By prouctSearchLayout = By.xpath("//div[contains(@class,'product-layout ')]");


    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    public boolean isSearchSuccessful(){
        List<WebElement> searchList = elementUtil.waitForElementsToBeVisible(prouctSearchLayout, AppConstants.Default_LargeTime_Out);

        if(searchList.size() > 0){
            System.out.println("Search successful...");
            LOG.info("Search successful...");
            return true;
        }
        return false;
    }

    public ProductInformationPage selectProduct(String productName){
        By product = By.xpath("//a[text()='"+productName+"']");
        elementUtil.doClick(product);
        LOG.info("Click on : " +productName);
        return new ProductInformationPage(driver);
    }
}
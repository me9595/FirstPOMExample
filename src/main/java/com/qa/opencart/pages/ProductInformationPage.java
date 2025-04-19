package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInformationPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private static final Logger LOG = Logger.getLogger(ProductInformationPage.class);

    By ProductImages = By.xpath("//ul[@class='thumbnails']//img");

    public ProductInformationPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Get the Product Header...")
    public String getProductHeader(String mainProductName){
        String xpath = "//h1[text()='"+mainProductName+"']";
        String productHeader = elementUtil.doGetText(By.xpath(xpath));
        LOG.info("Product Header is : " +productHeader);
        return productHeader;
    }

    @Step("Get the Product Image Count...")
    public int getProductImageCount(){
        int i = elementUtil.waitForElementsToBeVisible(ProductImages, AppConstants.Default_LargeTime_Out).size();
        LOG.info("Product image count is : " +i);
        return i;
    }

    @Step("Get the Product Page Title...")
    public String getProductPageTitle(String productTitle){
        String title = elementUtil.waitForTitleIs(AppConstants.Default_Time_Out, productTitle);
        LOG.info("Product Page Title : " +productTitle);
        return title;
    }

    @Step("Get the Product Page URL...")
    public void getProductPageURL(String url){
        elementUtil.waitForUrlContains(AppConstants.Default_Time_Out, url);
        LOG.info("Product Page URL : " +url);
    }
}

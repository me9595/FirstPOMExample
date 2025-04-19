package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.UUID;

public class RegisterPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private static final Logger LOG = Logger.getLogger(RegisterPage.class);

    private By firstName = By.xpath("//input[@name='firstname']");
    private By lastName = By.xpath("//input[@name='lastname']");
    private By email = By.xpath("//input[@name='email']");
    private By phoneNo = By.xpath("//input[@name='telephone']");
    private By password = By.xpath("//input[@name='password']");
    private By confirmPassword = By.xpath("//input[@name='confirm']");
    private By policyCHK = By.xpath("//input[@name='agree']");
    private By continueBtn = By.xpath("//input[@type='submit']");
    private By registrationSuccessMessage = By.xpath("//div[@id='content']//h1");
    private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");
    private By registerLink = By.xpath("(//a[text()='Register'])[2]");


    public RegisterPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    @Step("User Register Test...")
    public String userRegister(String firstName, String lastName, String phoneNo, String password){

        elementUtil.doSendKeysWithWait(this.firstName, AppConstants.Default_LargeTime_Out, firstName);
        LOG.info("Enter first name : " +firstName);
        elementUtil.doSendKeys(this.lastName,lastName);
        LOG.info("Enter last name : " +lastName);
        elementUtil.doSendKeys(this.email, elementUtil.generateRandamEmail());
        LOG.info("Enter email : " +email);
        elementUtil.doSendKeys(this.phoneNo,phoneNo);
        LOG.info("Enter phone no : " +phoneNo);
        elementUtil.doSendKeys(this.password,password);
        LOG.info("Enter password : " +password);
        elementUtil.doSendKeys(this.confirmPassword,password);
        LOG.info("Enter confirm password : " +password);
        elementUtil.doClick(this.policyCHK);
        LOG.info("Click on Policy check box");
        elementUtil.doClick(this.continueBtn);
        LOG.info("Click on Continue button : ");
        String message = elementUtil.waitForElementVisible(this.registrationSuccessMessage, AppConstants.Default_LargeTime_Out).getText();
        System.out.println("Success message .. " +message);
        LOG.info("Success message : " +message);
        elementUtil.doClick(logoutLink);
        LOG.info("Click on Logout link");
        elementUtil.doClick(registerLink);
        LOG.info("Click on Register link");
        return message;
    }

}
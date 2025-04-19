package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {

    @Description("Login page Correct user name and password test...")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeClass
    public void regSetUp(){
        registerPage = loginPage.nevigaterToregisterPage();
    }

    @DataProvider
    public Object[][] getRegisterTestData(){
        Object regTestData[][] = ExcelUtil.getTestData(AppConstants.Register_Sheet_Name);
        return regTestData;
    }

    @Description("Login page Register User test...")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getRegisterTestData")
    public void registerUserTest(String FirstName, String LastName, String PhoneNo, String Password){
        String actualMessage = registerPage.userRegister(FirstName, LastName, PhoneNo, Password);
        Assert.assertEquals(actualMessage, AppConstants.Account_Register_Success_Message);
    }
}

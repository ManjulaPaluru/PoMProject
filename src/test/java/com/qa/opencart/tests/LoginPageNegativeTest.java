package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ErrorUtil;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

    @DataProvider
    public Object[][] getLoginNegativeData() {
        return new Object[][]{
                {"test@gmail.com", "test@123"},
                {" ", "@#erdsf"},
                {"test123@123@mail.com", "@@@@"}
        };
    }

    @Test(dataProvider = "getLoginNegativeData")
    @Description("Login Title with invalid credentials.......")
    @Severity(SeverityLevel.NORMAL)
    public void LoginPageInvalidTest(String username, String password) {
        Assert.assertTrue(getLoginPage().doInvalidLogin(username, password), ErrorUtil.LOGIN_PAGE_ERROR_MESSAGE_NOT_DISPLAYED);
    }

}

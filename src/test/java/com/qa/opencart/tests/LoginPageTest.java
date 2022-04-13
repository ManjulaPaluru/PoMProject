package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ErrorUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.opencart.utils.Constants.LOGIN_PAGE_TITLE;
@Epic("Epic 100 - Design login page for open cart application")
@Story("US-101 - design login page features")
public class LoginPageTest extends BaseTest {

    @Test
    @Description("Login page Title Test...")
    @Severity(SeverityLevel.NORMAL)
    public void loginPageTitleTest() {
        String actualTitle = getLoginPage().getLoginPageTitle();
        System.out.println("actual title is: " + actualTitle);
        Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE, ErrorUtil.LOGIN_PAGE_TITlE_MISMATCH);
    }

    @Test
    @Description("Login page test...")
    @Severity(SeverityLevel.MINOR)
    public void loginPageUrlTest() {
        String url = getLoginPage().getLoginPageUrl();
        System.out.println(("url: " + url));
        Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
    }
    @Test
    @Description("Check forgot password Test")
    @Severity(SeverityLevel.NORMAL)
    public void forgotPwdLinkTest() {
        Assert.assertTrue(getLoginPage().isForgotPwdLinkExist());
    }

    @Test
    @Description("Login title test with currect username and password")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTest() {
        accPage = getLoginPage().doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accPage.isAccountsPageHeaderExist());
    }

    @Test
    @Description("Check forgot password link test....")
    @Severity(SeverityLevel.NORMAL)
    public void isRegisterLinkExistTest() {
        Assert.assertTrue(getLoginPage().isRegisterLinkExist());
    }
}

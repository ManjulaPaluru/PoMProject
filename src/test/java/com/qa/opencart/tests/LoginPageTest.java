package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qa.opencart.utils.Constants.LOGIN_PAGE_TITLE;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageTitleTest() {
        String actualTitle = loginpage.getLoginPageTitle();
        System.out.println("actual title is: " + actualTitle);
        Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE);
    }

    @Test
    public void loginPageUrlTest() {
        String url = loginpage.getLoginPageUrl();
        System.out.println(("url: " + url));
        Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
    }

    @Test
    public void forgotpwdLinkTest() {
        Assert.assertTrue(loginpage.isForgotPwdLinkExist());
    }

    @Test
    public void loginTest() {
        accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accpage.isAccountsPageHeaderExist());

    }

    @Test
    public void isRegisterLinkExistTest() {
        Assert.assertTrue(loginpage.isRegisterLinkExist());
    }
}

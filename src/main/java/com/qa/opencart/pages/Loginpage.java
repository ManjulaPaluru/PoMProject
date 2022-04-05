package com.qa.opencart.pages;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Loginpage {
    //here driver is pointing to null, for initilize  the driver we need to create constructor
    private WebDriver driver;
    private ElementUtil eleUtil;
    //private BY locators:
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgotpwd = By.linkText("Forgot your password?");
    private By registerLink = By.linkText("Register");

    //2.public page constructor
    public Loginpage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3.public page actions
    public String getLoginPageTitle() {
        return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
    }

    public String getLoginPageUrl() {
        return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
    }

    public boolean isForgotPwdLinkExist() {
        return eleUtil.doIsDisplayed(forgotpwd);
    }

    public AccountsPage doLogin(String un, String pwd) {
        eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
        return new AccountsPage(driver);
    }

    public boolean isRegisterLinkExist() {
        return eleUtil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();

    }

    public RegistrationPage navigateToRegisterPage() {
        if (isRegisterLinkExist()) {
            eleUtil.doClick(registerLink);
            return new RegistrationPage(driver);
        }
return  null;
    }
}
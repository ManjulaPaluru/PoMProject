package com.qa.opencart.pages;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ErrorUtil;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Loginpage {
    //here driver is pointing to null, for initilize  the driver we need to create constructor
    private final WebDriver driver;
    private final ElementUtil eleUtil;
    //private BY locators:
    private final By emailId = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By forgotpwd = By.linkText("Forgotten Password");
    private final By registerLink = By.linkText("Register");
    private final By loginErrorMessag = By.cssSelector("div.alert.alert-danger.alert-dismissible");

    //2.public page constructor
    public Loginpage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3.public page actions
    @Step("Getting login page Title ")
    public String getLoginPageTitle() {
        return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
    }
    @Step("Checking login page url...")
    public String getLoginPageUrl() {
        return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
    }
    @Step("Forgot password link is is displayed or not....")
    public boolean isForgotPwdLinkExist() {
        return eleUtil.doIsDisplayed(forgotpwd);
    }

    @Step("Login to the application with username{0} and password {1}")
    public AccountsPage doLogin(String un, String pwd) {
        eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
        return new AccountsPage(driver);
    }

    @Step("Login to application with invalid username{0} and password {1] ")
    public boolean doInvalidLogin(String un, String pwd) {
        eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
        String actualErrorMessage = eleUtil.doElementGetText(loginErrorMessag);
        log.info(actualErrorMessage);
        if (actualErrorMessage.contains(ErrorUtil.LOGIN_PAGE_ERROR_MESSAGE)) {
            return true;
        }
        return false;
    }

    @Step("Registration link is exist or not...")
    public boolean isRegisterLinkExist() {
        return eleUtil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();

    }

    @Step("navigate to registration page...")
    public RegistrationPage navigateToRegisterPage() {
        if (isRegisterLinkExist()) {
            eleUtil.doClick(registerLink);
            return new RegistrationPage(driver);
        }
return  null;
    }
}
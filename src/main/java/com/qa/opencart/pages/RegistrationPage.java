package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    By firstName = By.id("input-firstname");
    By lastName = By.id("input-lastname");
    By email = By.id("input-email");
    By telephone = By.id("input-telephone");
    By password = By.id("input-password");
    By conformPassword = By.id("input-confirm");
    By subscribeYes = By.xpath("//input[@name='newsletter' and @value='1']");
    By subscribeNo = By.xpath("//input[@name='newsletter' and @value='0']");
    By agreeCheckBox = By.name("agree");
    By continueBtn = By.xpath("//input[@value='Continue' and @type='submit']");
    By successMessage = By.cssSelector("div#content h1");
    By logoutLink = By.linkText("Logout");
    By registrationLink = By.linkText("Register");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public boolean accoutRegistration(
            String firstName, String lastName, String email, String telephone, String password, String subscribe) {

        eleUtil.doSendKeys(this.firstName, firstName);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.conformPassword, password);
        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYes);
        } else {
            eleUtil.doClick(subscribeNo);
        }

        eleUtil.doClick(agreeCheckBox);
        eleUtil.doClick(continueBtn);
        if (getAccountRegisterSuccessMessage().contains(Constants.REGISTER_SUCCESS_MESSG)){
            goToRegistration();
            return true;
        }
        return false;
    }

    public String getAccountRegisterSuccessMessage() {
        return eleUtil.waitForElementToBeVisible(successMessage, Constants.DEFAULT_TIME_OUT).getText();
    }

    private void goToRegistration() {
        eleUtil.doClick(logoutLink);
        eleUtil.doClick(registrationLink);
    }

}

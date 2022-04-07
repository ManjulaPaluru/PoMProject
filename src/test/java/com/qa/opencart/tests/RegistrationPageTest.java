package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationPageTest extends BaseTest {

    @BeforeClass
    public void regPageSetup(){
        registrationPage = getLoginPage().navigateToRegisterPage();
    }
    @Test
    public String getRandomEmail() {
        Random random = new Random();
        String email = "janautomation" + random.nextInt(1000) + "@gmail.com";
        return email;
    }

    @DataProvider
    public Object[][] getRegesterData(){
        return new Object[][]{
                {"Akhil","sag","1231231234","akhil@123","yes"},
                {"Medha","sag","8756567567","medha@123","no"}
        };

    }

    @Test(dataProvider = "getRegesterData")
    public void userRegistrationTest(String firstName,String lastName,String telephone,String password,String subscribe){
        Assert.assertTrue(registrationPage.accoutRegistration
                (firstName,lastName, getRandomEmail(),telephone,password,subscribe));

    }

}

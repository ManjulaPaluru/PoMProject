package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    public DriverFactory df;
    public Properties prop;
    public WebDriver driver;
    public Loginpage loginpage;
    public AccountsPage accpage;
    public SearchResultsPage searchResultsPage;
    public ProductInfoPage productInfoPage ;
    public RegistrationPage registrationPage;
    public SoftAssert softAssert;

    @BeforeTest
    public void setup(){
        System.out.println("@BeforeTest");
        df=new DriverFactory();
        prop=df.init_prop();
       driver= df.init_driver(prop);
      loginpage =new Loginpage(driver);

    }

    @AfterTest
    public void tearDown(){
        System.out.println("@AfterTest");
        driver.quit();
    }

}


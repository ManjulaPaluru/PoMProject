package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

@Listeners(value = {AnnotationTransformer.class, TestAllureListener.class, ExtentReportListener.class})
@Slf4j
@Getter
public class BaseTest {

    public DriverFactory df;
    public Properties prop;
    public WebDriver driver;
    private Loginpage loginPage;
    public AccountsPage accPage;
    public SearchResultsPage searchResultsPage;
    public ProductInfoPage productInfoPage ;
    public RegistrationPage registrationPage;
    public SoftAssert softAssert;

    @BeforeTest
    public void setup(){
//        System.out.println("@BeforeTest");
        log.info("@BeforeTest");
        df=new DriverFactory();
        prop=df.init_prop();
       driver= df.init_driver(prop);
      loginPage =new Loginpage(driver);

    }

    @AfterTest
    public void tearDown(){
//        System.out.println("@AfterTest");
        log.info("@AfterTest");
        driver.quit();
    }

}

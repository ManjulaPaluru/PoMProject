package com.qa.opencart.factory;

import com.qa.opencart.utils.Browser;
import com.qa.opencart.utils.ErrorUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This method is used to initialize the webdriver on the basis of given browser
     * name. This method will take care of local and remote execution
     *
     * @return
     */
    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("browser name is : " + browserName);
        highlight = prop.getProperty("highlight").trim();
        optionsManager = new OptionsManager(prop);

        if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
            //suppose if we want to use system.SetProperty instead of webDriverManager third party tool,
            // in that time we can meantain our executable files in  Browser interface
            //System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY,Browser.CHROME_DRIVER_PATH);
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE)) {
            tlDriver.set(new SafariDriver());
        } else {
            log.info(ErrorUtil.BROWSER_NOT_FOUND_ERROR_MESSAGE + browserName);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().fullscreen();
        getDriver().get(prop.getProperty("url"));

        return getDriver();

    }

    /**
     * this will return the thread local copy of the webdriver(driver)
     *
     * @return
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * This method is used to initialize the properties on the basis of given
     * environment: QA/DEV/Stage/PROD
     *
     * @return this returns prop
     */
    public Properties init_prop() {
        prop = new Properties();
        FileInputStream ip = null;

        //mvn clean install -Denv="qa"
        //mvn clean install
        // by default qa environment will run
        String envName = System.getProperty("env");
        System.out.println("Running tests on environment: " + envName);
        if (envName == null) {
            System.out.println("No environment is given , hence running it on QA ");
            try {
                ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                    case "dev":
                    case "stage":
                    case "uat":
                    case "prod":
                        ip= new FileInputStream(("./src/test/resources/config/"+envName+".config.properties"));
                        break;
                    default:
                        System.out.println("Please pass the right environment : " + envName);
                        throw new RuntimeException("env: " + envName + " parameter is not supported");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * take screenshot
     */
    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }

}

package com.qa.opencart.factory;

import com.qa.opencart.error.AppError;
import com.qa.opencart.exception.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;


public class Driverfactory {

    public WebDriver driver;
    public Properties prop;
    public OptionsManager optionsManager;

    private static final Logger LOG = Logger.getLogger(Driverfactory.class);

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public static String highlight;

    // this method is used for the init the properties file
    public Properties initProp(){
        prop = new Properties();
        FileInputStream ip = null;

        //String envName = System.getenv("env"); //qa/uat/sit etc
        String envName = System.getProperty("env"); //qa/uat/sit etc
        System.out.println("------> Running test cases on environment : " +envName);
        LOG.info("------> Running test cases on environment : " +envName);

        if(envName == null){
            System.out.println("No environment is given... hence running in QA environment");
            LOG.info("No environment is given... hence running in QA environment");

            try {
                ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                switch (envName) {
                    case "qa":
                        ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
                        LOG.info("Select environment as : QA");
                        break;

                    case "uat":
                        ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
                        LOG.info("Select environment as : UAT");
                        break;

                    case "sit":
                        ip = new FileInputStream("./src/main/resources/config/config.properties");
                        LOG.info("Select environment as : SIT");
                        break;

                    default:
                        System.out.println("Please pass the right environment : " +envName);
                        LOG.info("Please pass the right environment : " +envName);
                        throw new FrameworkException(AppError.NO_CORRECT_ENVIRONMENT_FOUND_EXCEPTION);
                        //break;
                }
            }catch (FileNotFoundException e) {
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


    // this method is used to init the driver on the bases of browser name
    public WebDriver initDriver(Properties prop){

        String browserName = prop.getProperty("browser").toLowerCase();
        System.out.println("browser name : " +browserName);
        LOG.info("Browser name is : " +browserName);
        highlight = prop.getProperty("highlight").trim();
        optionsManager = new OptionsManager(prop);

        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver();
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            LOG.info("Chrome browser is opened successfully...");
        }
        else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver();
            tlDriver.set(new FirefoxDriver());
            LOG.info("Firefox browser is opened successfully...");
        }
        else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            //driver = new EdgeDriver();
            tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
            LOG.info("Edge browser is opened successfully...");
        }
        else {
            System.out.println("Please pass the right browser name : " +browserName);
            LOG.error("Please pass the right browser name : " +browserName);
            throw new FrameworkException(AppError.NO_BROWSER_FOUND_EXCEPTION);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        LOG.info("Application URL is : " +prop.getProperty("url"));

        return getDriver();
    }

    public static synchronized WebDriver getDriver(){
        return tlDriver.get();
    }

    /**
     * take screenshot
     *
     */
    public static String getScreenshot() {

        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);

        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}

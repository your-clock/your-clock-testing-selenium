package org.yourclock;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.testng.annotations.Test;
import org.yourclock.pageobject.login;

public class TestLogin {

    private WebDriver driver;
    login PageObjectsLogin;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Starting test case");
        System.out.println("System to use: " + System.getProperty("os.name"));
        if(System.getProperty("os.name").equals("Mac OS X")){
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver");
        }else{
            System.setProperty("webdriver.chrome.driver", "/usr/local/share/chrome_driver/chromedriver");
        }
        System.out.println("Starting web driver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        driver = new ChromeDriver(chromeOptions);
        PageObjectsLogin = new login(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://your-clock-dev.netlify.app/#/auth");
    }

    @Test
    public void testLogin(){
        try{
            PageObjectsLogin.setEmail("automatizacion.pse@gmail.com");
            PageObjectsLogin.setPassword("97122110420");
            PageObjectsLogin.clickButtonContinue();
        }catch (Exception error){
            System.out.println("ERROR: "+error);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult){
        if(testResult.getStatus() == ITestResult.SUCCESS){
            System.out.println("EXITOSO");
        }else{
            System.out.println("FALLIDO");
        }
        driver.quit();
    }

}

package org.yourclock.pageobject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class login {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input#input-email")
    WebElement inputEmail;

    @FindBy(css = "input#input-pwd")
    WebElement inputPassword;

    @FindBy(css = "button#btn-login")
    WebElement btnLogin;

    public login(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }

    public void setEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.click();
        inputEmail.sendKeys(email);
        System.out.println("Setting input email");
    }

    public void setPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.click();
        inputPassword.sendKeys(password);
        System.out.println("Setting input password");
    }

    public void clickButtonContinue(){
        wait.until(ExpectedConditions.visibilityOf(btnLogin));
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
        System.out.println("Clicking button login");
    }

}

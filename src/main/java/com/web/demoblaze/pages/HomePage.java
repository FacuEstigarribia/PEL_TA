package com.web.demoblaze.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class HomePage {


    protected WebDriver driver;
    @FindBy(xpath = "//a[@id='signin2']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//div[@id='signInModal']")
    private WebElement signUpModal;

    @FindBy(xpath = "//input[@id='sign-username']")
    private WebElement usernameFieldSignUp;

    @FindBy(xpath = "//input[@id='sign-password']")
    private WebElement passwordFieldSignUp;

    @FindBy(xpath = "//div[@id='signInModal']//div[@class='modal-footer']/button[2]")
    private WebElement signUpBtnModal;

    @FindBy(xpath = "//a[@id='login2']")
    private WebElement logInBtn;

    @FindBy(xpath = "//input[@id='loginusername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='loginpassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='logInModal']/div/div/div[3]/button[2]")
    private WebElement logInBtnModal;

    @FindBy(xpath = "//a[@id='nameofuser']")
    private WebElement welcomeNameOfUser;

    @FindBy(xpath = "/html/body/div[5]/div/div[1]/div/a[4]")
    private WebElement monitorsBtn;

    @FindBy(xpath = "//div[@class='row' and @id='tbodyid']/div")
    private List<WebElement> monitorsResult;

//    @FindBy(xpath = "//div[@id='logInModal']")
//    private WebElement logInModal;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void sendLogInData(String username, String password){
        logInBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='logInModal']")));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        clickLogInModal();

    }

    public String signUpUser(String username, String password){
        signUpBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpModal));
        usernameFieldSignUp.sendKeys(username);
        passwordFieldSignUp.sendKeys(password);
        signUpBtnModal.click();
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }



    public String getNameOfUser(){
        String name = welcomeNameOfUser.getText();
        System.out.println("Name of user: " + welcomeNameOfUser.getText());
        return name;
    }


    public void searchMonitors(){
        monitorsBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements((monitorsResult)));

        if (!monitorsResult.isEmpty()) {
            System.out.println(monitorsResult.size());
            for (WebElement result : monitorsResult) {
                System.out.println(result.getText());
            }
        } else {
            System.out.println("La lista de resultados de Monitors está vacía.");
        }
    }

    private void clickLogInModal(){
        logInBtnModal.click();
    }


}

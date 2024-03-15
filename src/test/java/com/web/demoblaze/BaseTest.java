package com.web.demoblaze;
//
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import com.web.demoblaze.pages.HomePage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Unit test for simple App.
 */
public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;
    protected HomePage homePage;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        loadBaseUrl();
        goHomePage();
    }
    @BeforeMethod
    public void goHomePage(){
        driver.get(baseUrl);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void recordFailure(@NotNull ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot.toPath(), new File("resources/screenshots/" + result.getName()).toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

    private void loadBaseUrl() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Lo siento, no se puede encontrar el archivo config.properties");
                return;
            }

            // carga un conjunto de propiedades desde el archivo de propiedades
            prop.load(input);

            // obtén el valor de la clave 'base_url' y guárdalo en la variable baseUrl
            baseUrl = prop.getProperty("base_url");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

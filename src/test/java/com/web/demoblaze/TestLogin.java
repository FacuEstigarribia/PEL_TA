package com.web.demoblaze;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.testng.Assert.*;

public class TestLogin extends BaseTest{


    private String username;
    private String password;

    @Test
    public void testLogIn(){
        loadCredentials();
        homePage.sendLogInData(username, password);
        Assert.assertEquals("Welcome demoUser_F", homePage.getNameOfUser(), "Error in Name of user. Is not that was expected");

    }

    private void loadCredentials() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("test_data.properties")){

            prop.load(input);

            // Obtener las credenciales
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

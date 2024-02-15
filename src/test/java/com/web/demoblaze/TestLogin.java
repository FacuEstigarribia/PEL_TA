package com.web.demoblaze;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static org.testng.Assert.assertEquals;

public class TestLogin extends BaseTest{


    private static String username;
    private static String password;

    static {
        Properties prop = new Properties();
        try (InputStream input = TestLogin.class.getClassLoader().getResourceAsStream("test_data.properties")){

            prop.load(input);

            // Obtener las credenciales
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogIn(){
        homePage.sendLogInData(username, password);
        assertEquals("Welcome demoUser_F", homePage.getNameOfUser(), "Error in Name of user.");

    }

}

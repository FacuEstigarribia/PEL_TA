package com.web.demoblaze;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestSignUp extends BaseTest{


    private static String usernameSignUp;
    private static String password;

    static {
        Properties prop = new Properties();
        try (InputStream input = TestSignUp.class.getClassLoader().getResourceAsStream("test_data.properties")){

            prop.load(input);

            // Obtener las credenciales
            usernameSignUp = prop.getProperty("sign_up_user");
            password = prop.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSignUpUser(){
        String textAlert = homePage.signUpUser(usernameSignUp, password);
        Assert.assertEquals(textAlert, "Sign up successful.", "Error in text expected");
    }

}

package com.thinkAndGetIt.frontend.tests;

import com.thinkAndGetIt.frontend.utils.ConfigReader;
import com.thinkAndGetIt.frontend.base.BaseTest;
import com.thinkAndGetIt.frontend.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToLoginPage() {
        loginPage= new LoginPage(page);
        loginPage.navigateToSignIn();
    }

    @Test
    public void successfulLoginTest(){
        String email= ConfigReader.get("admin.email");
        String password= ConfigReader.get("admin.password");
        loginPage.login(email, password);
        page.waitForTimeout(3000);
        String currentUrl= loginPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login"));
    }

    @Test
    public void loginWithInvalidEmailTest(){
        String email= "you@example.com";
        String password =  ConfigReader.get("admin.password");
        loginPage.login(email, password);
        page.waitForTimeout(3000);
        String currentUrl= loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
        Assert.assertTrue(loginPage.getEmailInput().isEmpty());
        Assert.assertTrue(loginPage.getPasswordInput().isEmpty());
    }

    @Test
    public void loginWithInvalidPasswordTest(){
        String email= ConfigReader.get("admin.email");
        String password = "12345";
        loginPage.login(email, password);
        page.waitForTimeout(3000);
        String currentUrl= loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
        Assert.assertTrue(loginPage.getEmailInput().isEmpty());
        Assert.assertTrue(loginPage.getPasswordInput().isEmpty());
    }

}

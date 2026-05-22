package com.thinkAndGetIt.frontend.tests;

import com.thinkAndGetIt.frontend.base.BaseTest;
import com.thinkAndGetIt.frontend.pages.RegistrationPage;
import com.thinkAndGetIt.frontend.utils.ConfigReader;
import com.thinkAndGetIt.frontend.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void navigateToRegistrationPage() {
        registrationPage = new RegistrationPage(page);
        registrationPage.navigateToCreateAccount();
    }

    @Test
    public void successfullRegisterTest(){
        registrationPage.createAccount(TestData.generateFirstName(),
                TestData.generateLastName(),
                TestData.generateEmail(), TestData.validPassword);

        page.waitForTimeout(3000);
        String currentUrl= registrationPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("register"));
    }

    @Test
    public void duplicateEmailTest(){
        String email= ConfigReader.get("admin.email");
        registrationPage.createAccount(TestData.generateFirstName(),
                TestData.generateLastName(),
                email, TestData.validPassword);

        page.waitForTimeout(3000);
        String currentUrl= registrationPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("register"));
        Assert.assertTrue(registrationPage.isMessageVisible("Registration failed"));
    }

    @Test
    public void invalidEmailFormatTest(){
        registrationPage.createAccount(TestData.generateFirstName(),
                TestData.generateLastName(),
                TestData.invalidEmail, TestData.validPassword);

        page.waitForTimeout(3000);
        String currentUrl= registrationPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("register"));
    }

    @Test
    public void missingFirstNameTest(){
        registrationPage.createAccount("",
                TestData.generateLastName(),
                TestData.invalidEmail, TestData.validPassword);

        page.waitForTimeout(3000);
        String currentUrl= registrationPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("register"));
    }

    @Test
    public void missingAllFieldsTest(){
        registrationPage.createAccount("", "", "", "");

        page.waitForTimeout(3000);
        String currentUrl= registrationPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("register"));
    }




}


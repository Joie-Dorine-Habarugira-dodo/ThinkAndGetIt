package com.thinkAndGetIt.frontend.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegistrationPage {
    private Page page;

    private String getStartedButton= "a[href='/register']";
    private String firstNameInput= "input[placeholder='John']";
    private String lastNameInput= "input[placeholder='Doe']";
    private String emailInput= "input[type='email']";
    private String passwordInput= "input[type='password']";
    private String createAccountButton= "button[type='submit']";


    public RegistrationPage(Page page) {
        this.page = page;
    }

    public void navigateToCreateAccount(){
        page.click(getStartedButton);
    }

    public void createAccount(String firstName, String lastName, String email, String password){
        page.fill(firstNameInput,firstName);
        page.fill(lastNameInput,lastName);
        page.fill(emailInput,email);
        page.fill(passwordInput,password);
        page.click(createAccountButton);
    }

    public String getCurrentUrl(){
        return page.url();
    }

    public String getEmailInput() {
        return page.inputValue(emailInput);
    }
    public String getPasswordInput() {
        return page.inputValue(passwordInput);
    }
    public boolean isMessageVisible(String message) {
        return page.getByText(message).isVisible();
    }

}


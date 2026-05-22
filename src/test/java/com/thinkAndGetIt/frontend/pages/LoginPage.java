package com.thinkAndGetIt.frontend.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    private String signInButton= "a[href='/login']";
    private String emailInput= "input[type='email']";
    private String passwordInput= "input[type='password']";
    private String loginButton= "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToSignIn(){
        page.click(signInButton);
    }

    public String getCurrentUrl(){
        return page.url();
    }

    public void login(String email, String password) {
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.click(loginButton);

    }

    public String getEmailInput() {
        return page.inputValue(emailInput);
    }
    public String getPasswordInput() {
        return page.inputValue(passwordInput);
    }

}

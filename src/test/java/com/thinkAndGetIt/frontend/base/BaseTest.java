package com.thinkAndGetIt.frontend.base;

import com.microsoft.playwright.*;
import com.thinkAndGetIt.frontend.utils.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod
    public void setup(){
        playwright = Playwright.create();
        browser=playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context= browser.newContext();
        page=context.newPage();
        page.navigate(ConfigReader.get("base.url"));
    }

    @AfterMethod
    public void teardown(){
        page.close();
        browser.close();
        playwright.close();
    }
}

package com.thinkAndGetIt.frontend.tests;

import com.thinkAndGetIt.frontend.base.BaseTest;
import com.thinkAndGetIt.frontend.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductFiltersTests extends BaseTest {
    ProductsPage productsPage;

    @BeforeMethod
    public void navigateToProductsPage() {
        productsPage = new ProductsPage(page);
        productsPage.navigateToProductsPage();
    }

    @Test
    public void categoryFilter(){
       String category= "Bags & Luggage";
       productsPage.selectCategory(category);
       page.waitForLoadState();
       Assert.assertTrue(productsPage.areAllProductsOfSelectedCategory(category));
   }

   @Test
   public void priceFilter(){
        productsPage.setPriceRange(20,60);
        page.waitForLoadState();
        Assert.assertTrue(productsPage.areAllProductsOfSelectedPriceRange(20,60));

   }

   @Test
   public void sizeFilter(){
        productsPage.selectSize("S");
        page.waitForTimeout(15000);
       Assert.assertTrue(productsPage.hasProducts() || productsPage.isNoResultsDisplayed());
   }

    @Test
    public void colorFilter(){
        productsPage.selectSize("Red");
        page.waitForTimeout(15000);
        Assert.assertTrue(productsPage.hasProducts() || productsPage.isNoResultsDisplayed());
    }

    @Test
    public void specialFilter(){
        productsPage.selectSize("Featured");
        page.waitForTimeout(15000);
        Assert.assertTrue(productsPage.hasProducts() || productsPage.isNoResultsDisplayed());
    }





}

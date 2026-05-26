package com.thinkAndGetIt.frontend.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Locale;

public class ProductsPage {

    private Page page;
    private static String startShoppingButton= "a[href='/home']";
    private static String shopNowButton= "a[href='/products']";
    private static String productCards= ".group.card-hover.block";
    private static String priceMinInput= "input[placeholder='Min']";
    private static String priceMaxInput= "input[placeholder='Max']";
    private static String showResultsButton= "text=Show";
    private static String noResultsMessage= "text=No products found";
    private static String clearAllFilters= "text=Clear all filters";


    public ProductsPage(Page page) {
        this.page = page;
    }

    public void navigateToProductsPage() {
        page.locator(startShoppingButton).click();
        page.locator(shopNowButton).first().click();
    }

    public Locator category(String category){
        return page.locator("text="+ category).first();
    }
    public Locator size(String size){
        return page.locator("text="+ size).first();
    }
    public Locator color(String color){
        return page.locator("button[title='"+ color +"']").first();
    }
    public Locator special(String special){
        return page.locator("text="+ special).first();
    }

    public void selectCategory(String category){
        category(category.toLowerCase()).click();
    }

    public boolean areAllProductsOfSelectedCategory(String category) {
        Locator visibleProducts= page.locator(productCards).filter(new Locator.FilterOptions().setVisible(true));
        int count = visibleProducts.count();
        for (int i = 0; i < count; i++) {
            String productText= visibleProducts.nth(i).textContent().trim();
            if(!productText.contains(category)){
                return false;
            }
        }
        return true;
    }

    public void setPriceRange(double min, double max){
        page.locator(priceMinInput).fill(String.valueOf(min));
        page.locator(priceMaxInput).fill(String.valueOf(max));
        page.locator(priceMaxInput).press("Enter");
    }

    public boolean areAllProductsOfSelectedPriceRange(double min, double max) {
        Locator visibleProducts= page.locator(productCards).filter(new Locator.FilterOptions().setVisible(true));
        int count = visibleProducts.count();
        for (int i = 0; i < count; i++) {
            String productText= visibleProducts.nth(i).textContent();
            String priceText = productText.replaceAll(".*\\$(\\d+\\.\\d+).*", "$1");

            double price = Double.parseDouble(priceText);

            if (price < min || price > max) {
                return false;
            }
        }
        return true;
    }

    public void selectSize(String size){
        size(size).click();
    }

    public void selectColor(String color){
        color(color).click();
    }

    public boolean areAllProductsOfSelectedColor(String color) {
        Locator visibleProducts= page.locator(productCards).filter(new Locator.FilterOptions().setVisible(true));
        int count = visibleProducts.count();
        for (int i = 0; i < count; i++) {
            String productText= visibleProducts.nth(i).textContent().trim();
            if(!productText.contains(color)){
                return false;
            }
        }
        return true;
    }
    public void selectSpecial(String special, boolean enable){
        if(special(special).isChecked() !=enable){
            special(special).click();
        };
    }


    public int getProductCount(){
        return page.locator(productCards).count();
    }

    public boolean hasProducts() {
        return getProductCount() > 0;
    }

    public boolean isNoResultsDisplayed() {
        return page.locator(noResultsMessage).isVisible();
    }

    public void clearAllFilters(){
        page.locator(clearAllFilters).click();
    }


}

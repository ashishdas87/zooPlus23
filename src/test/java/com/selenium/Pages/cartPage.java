package com.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.selenium.Stepdefination.Hooks.driver;
import static com.selenium.Utils.commonMethods.*;

public class cartPage {

    static String buttonAcceptNecessaryCookies      = "//button[@id='onetrust-reject-all-handler']";
    static String buttonContinueShopping            = "//button[@data-zta='continueShoppingBtn']";
    static String buttonsAddToCart1                 = "//*[@id='splide01-slide01']//button";
    static String buttonsAddToCart2                 = "//*[@id='splide02-slide02']//button";
    static String buttonsAddToCart3                 = "//*[@id='splide03-slide03']//button";
    static String productTotalPrices                = "//div[@data-zta=\"FlatBoxUIC\"]/article/section[2]/article//div[@data-zta='articleQuantitySubtotal']";
    static String totalPriceOnCart                  =  "//h3[@data-zta='total__price__value']";
    static String shippingCost                      =   "//p[@data-zta='shippingCostValueOverview']";
    static String shippingCountry                   = "//a[@data-zta='shippingCountryName']";
    static String selectShippingCountry             = "//button[@data-zta='dropdownMenuTriggerButton']";
    static String buttonPortugal                    = "//button/li[@data-label='Portugal']";
    static String inputPostcode                     = "//input[@data-zta='inputInput']";
    static String buttonUpdate                      = "//button[@data-zta='shippingCostPopoverAction']";

    public static void openCartPage() throws Exception{
        waitForDisplayOfWebElement(By.xpath(buttonContinueShopping));
        clickWithJavaScript(buttonAcceptNecessaryCookies);
    }
    public static void addProductsToCart() throws Exception {
        waitForDisplayOfWebElement(By.xpath(buttonsAddToCart1));
        clickWithJavaScript(buttonsAddToCart1);
        waitForDisplayOfWebElement(By.xpath(buttonsAddToCart2));
        clickWithJavaScript(buttonsAddToCart2);
        waitForDisplayOfWebElement(By.xpath(buttonsAddToCart3));
        clickWithJavaScript(buttonsAddToCart3);
        waitForDisplayOfWebElement(By.xpath(buttonsAddToCart3));

    }
    public static void getUrlAndVerify(){
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("/cart"));
    }
    static List<WebElement> products = driver.findElements(By.xpath(productTotalPrices));
    static float  allProductValues [];
    static String prodPrice1= "//div[@data-zta='FlatBoxUIC']/article[";
    static String prodPrice2= "]/section[2]/article//div[@data-zta='articleQuantitySubtotal']";

    static String productPrice,productIndividualTotalPrice;;
    static Float productIndividualPrice=0.0f;
    static Float minProductPrice = 0.0F, maxProductPrice = 0.0F;
    public static void arrangePriceOnDescendingOrder() throws Exception {

        List<WebElement> products = driver.findElements(By.xpath(productTotalPrices));
        for (int i = 1; i <= products.size() ; i++) {
            waitForDisplayOfWebElement(By.xpath(prodPrice1 + i + prodPrice2));
            productPrice = driver.findElement(By.xpath(prodPrice1 + i + prodPrice2)).getText();

            System.out.println("Product total Value:" + productPrice);
            String pp[] = productPrice.split("€");
            productIndividualPrice = Float.parseFloat(pp[1]);
            if (i == 1)
                minProductPrice = productIndividualPrice;
            if (productIndividualPrice <= minProductPrice)
                minProductPrice = productIndividualPrice;
            if (productIndividualPrice >= maxProductPrice)
                maxProductPrice = productIndividualPrice;
            allProductValues = new float[products.size() + 1];
            allProductValues[i - 1] = productIndividualPrice;
        }
    }
        public static void incrementingTheQuantityWithLowerPriceAndDeletingTheHighPriceProduct()throws Exception {
        List<WebElement> products = driver.findElements(By.xpath(productTotalPrices));
        for (int i=1; i<=products.size();i++) {

            try
            {
                productIndividualTotalPrice = driver.findElement(By.xpath(prodPrice1+i+prodPrice2)).getText();
            }
            catch(Exception NoSuchElementException)
            {
                waitForDisplayOfWebElement(By.xpath(prodPrice1+(i-1)+prodPrice2));
                productIndividualTotalPrice = driver.findElement(By.xpath(prodPrice1+(i-1)+prodPrice2)).getText();
            }

            if (productIndividualTotalPrice.contains("€" + minProductPrice.toString())) {
               try
               {
                   clickWithJavaScript("//div[@data-zta=\"FlatBoxUIC\"]/article[" + i + "]/section[2]/article//button[@data-zta='quantityStepperIncrementButton']");
               }
               catch(Exception NoSuchElementException)
               {
                   clickWithJavaScript("//div[@data-zta=\"FlatBoxUIC\"]/article[" + (i-1) + "]/section[2]/article//button[@data-zta='quantityStepperIncrementButton']");

               }
            }
            if (productIndividualTotalPrice.contains("€" + maxProductPrice.toString())) {
                try
                {
                    clickWithJavaScript("//div[@data-zta=\"FlatBoxUIC\"]/article[" + (i)+ "]/section[2]/article//button[@data-zta='quantityStepperDecrementButton']");
                }
                catch(Exception NoSuchElementException)
                {
                    clickWithJavaScript("//div[@data-zta=\"FlatBoxUIC\"]/article[" + (i-1) + "]/section[2]/article//button[@data-zta='quantityStepperDecrementButton']");
                }
            }
        }

    }
    static String shippingPrice ;
    public static void verifySubtotalTotalPriceOfProducts() throws Exception{
        float subtotalProductPrice=0,totalShippingPrice=0,finalPriceOnCart=0;
        String totalProductPrice="";
        List<WebElement> products = driver.findElements(By.xpath(productTotalPrices));
        for (int i=1; i<=products.size();i++) {
            waitForDisplayOfWebElement(By.xpath(prodPrice1 + i + prodPrice2));
            productPrice = driver.findElement(By.xpath(prodPrice1 + i + prodPrice2)).getText();
            String pp[] = productPrice.split("€");
            productIndividualPrice = Float.parseFloat(pp[1]);
            subtotalProductPrice = subtotalProductPrice + productIndividualPrice;
            shippingPrice=driver.findElement(By.xpath(shippingCost)).getText();
          if(shippingPrice.contains("Free")==false){
              String sp[] = shippingPrice.split("€");
              totalShippingPrice = Float.parseFloat(sp[1]);
          }

        }
        if(shippingPrice.contains("Free")==false)
        {
            finalPriceOnCart = subtotalProductPrice + totalShippingPrice;
            totalProductPrice = driver.findElement(By.xpath(totalPriceOnCart)).getText();
            Assert.assertEquals(totalProductPrice, "€" + Float.toString(finalPriceOnCart));
        }
        else {
            finalPriceOnCart = subtotalProductPrice ;
            totalProductPrice = driver.findElement(By.xpath(totalPriceOnCart)).getText();
            Assert.assertEquals(totalProductPrice, "€" + Float.toString(finalPriceOnCart));
        }
    }

    public static void changeShippingCountryAndPostCode (String country,String postcode)throws InterruptedException{
        clickWithJavaScript(shippingCountry);
        clickWithJavaScript(selectShippingCountry);
        clickWithJavaScript(buttonPortugal);
        clearAndEnterText(inputPostcode,postcode);
        clickWithJavaScript(buttonUpdate);

    }
}

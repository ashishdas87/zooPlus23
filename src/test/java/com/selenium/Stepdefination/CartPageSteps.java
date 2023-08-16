package com.selenium.Stepdefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.selenium.Pages.cartPage.*;
import static com.selenium.Utils.commonMethods.*;

public class CartPageSteps {

	@Given("Open the browser and cart page")
	public void open_the_browser_and_cart_page() throws Exception{
		openCartPage();
	}

	@When("Select the multiple products from the recommendations")
	public void select_the_multiple_products_from_the_recommendations() throws Exception {
		addProductsToCart();
	}

	@Then("verify the url contains “\\/cart”")
	public void verify_the_url_contains_cart() {
		getUrlAndVerify();
	}

	@Then("Getting all product prices and sorting them in descending order")
	public void getting_all_product_prices_and_sorting_them_in_descending_order()throws Exception {
		arrangePriceOnDescendingOrder();
	}

	@Then("Incrementing by one the quantity of the product with the lowest price and Delete the product with the highest price")
	public void incrementing_by_one_the_quantity_of_the_product_with_the_lowest_price_and_delete_the_product_with_the_highest_price() throws Exception {
		incrementingTheQuantityWithLowerPriceAndDeletingTheHighPriceProduct();
	}


	@Then("Verify that the subtotal and total prices are correct.")
	public void verify_that_the_subtotal_and_total_prices_are_correct()throws Exception {
		verifySubtotalTotalPriceOfProducts();
	}

	@Then("^Change the shipping \"([^\"]*)\" with \"([^\"]*)\"\\.$")
	public void change_the_shipping_with(String country, String postcode) throws Throwable {
		changeShippingCountryAndPostCode(country,postcode);
	}
}

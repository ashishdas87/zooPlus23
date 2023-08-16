#Author: ashish.1987.das@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business Operations on Cart Page.

@zooplus
Feature: Business Operations on Cart Page.
  
    Scenario Outline: Adding multiple products from the recommendations and Edit the quantity as per requirement
      Given Open the browser and cart page
      When Select the multiple products from the recommendations
      Then verify the url contains “/cart”
      And Getting all product prices and sorting them in descending order
      And Incrementing by one the quantity of the product with the lowest price and Delete the product with the highest price
      Then Verify that the subtotal and total prices are correct.
      And Change the shipping "<country>" with "<postcode>".

      Examples:
        | country  | postcode |
        | Portugal | 5000     |
      
# pfseven-company
An intermedia project issued by Project Future 7 powered by ReGeneration.
This assignment aimed to familiarize us with Java, Maven, intelliJ IDEA,
and many other tech tools as well as to commonly known software development techniques,
learned during the first week of training.

The task was to develop an e-shop using plain-vanilla Java in a group with other participants.

# Basic Functionality
## Description
**The system**
- load/stores a list of products when the system is launched/shut it down.
- create new customers.
- create an order containing one or more products represented as order items.

All information and action results are persisted in H2 database.

- At least 20 different products are initially loaded in the database.
- The administrator of the system handles everything using a text-based menu of options.

## Ordering and discount policy
**Description**

   Based on the system’s requirements, the customer can buy products either by paying with
   cash or by using a credit card. Furthermore, customers from the
   - B2C (Individual),
   - B2B (Business),
   - B2G (Government)

   categories are served. The system distinguishes purchase methods and customer category,
   as the following price discounts apply when buying a product:
   - Individuals get no discount
   - Business users get a 20% discount
   - Government users get a 50% discount
   - 10% discount when the customer pays by wire transfer
   - 15% discount when the customer uses a credit card

   Please note that every customer can have only one order pending i.e. an order that has not
   been saved to the database.
   E.g., a government customer purchasing a product using their credit card will receive a price
   reduction of 65% (50% + 15%).

## Reports
**Description**

   Based on the purchases of each customer, the system support the following reports:

   - Total number and cost of purchases for a particular customer
   - Total number and cost of purchases per customer category
   - Total number and cost of purchases per payment method
   - The customer(s) who purchased the most expensive product and how many times

## Exception handling
**Description**

   The system has a central exception handling mechanism which is managing any
   possible error that happens during operation.
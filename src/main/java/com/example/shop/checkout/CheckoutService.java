package com.example.shop.checkout;

import com.example.shop.inventory.InventoryService;
import com.example.shop.pricing.DiscountService;
import com.example.shop.pricing.PriceCalculator;
import com.example.shop.tax.TaxService;

/** Orchestrates the other services; it is the only class that touches all of them. */
public class CheckoutService {

    private final PriceCalculator prices;
    private final DiscountService discounts;
    private final TaxService taxes;
    private final InventoryService inventory;

    public CheckoutService(PriceCalculator prices, DiscountService discounts,
                           TaxService taxes, InventoryService inventory) {
        this.prices = prices;
        this.discounts = discounts;
        this.taxes = taxes;
        this.inventory = inventory;
    }

    public double checkout(String sku, double unitPrice, int qty, String state) {
        if (!inventory.reserve(sku, qty)) {
            throw new IllegalStateException("Insufficient stock for " + sku);
        }
        double subtotal = prices.lineTotal(unitPrice, qty);
        double discounted = discounts.applyDiscount(subtotal);
        double tax = taxes.taxFor(state, discounted);
        return Math.round((discounted + tax) * 100.0) / 100.0;
    }
}

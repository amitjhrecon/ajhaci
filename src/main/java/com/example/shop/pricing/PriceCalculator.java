package com.example.shop.pricing;

public class PriceCalculator {

    public double lineTotal(double unitPrice, int quantity) {
        if (unitPrice < 0 || quantity < 0) {
            throw new IllegalArgumentException("price and quantity must be >= 0");
        }
        return round(unitPrice * quantity);
    }

    public double cartTotal(double[] unitPrices, int[] quantities) {
        if (unitPrices.length != quantities.length) {
            throw new IllegalArgumentException("arrays must be the same length");
        }
        double total = 0;
        for (int i = 0; i < unitPrices.length; i++) {
            total += lineTotal(unitPrices[i], quantities[i]);
        }
        return round(total);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}

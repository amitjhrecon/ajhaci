package com.example.shop.pricing;

public class DiscountService {

    /** 5% off over 100, 10% off over 500, 15% off over 1000. */
    public double applyDiscount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be >= 0");
        }
        double rate = 0;
        if (amount > 1000) {
            rate = 0.15;
        } else if (amount > 500) {
            rate = 0.10;
        } else if (amount > 100) {
            rate = 0.05;
        }
        return Math.round(amount * (1 - rate) * 100.0) / 100.0;
    }
}

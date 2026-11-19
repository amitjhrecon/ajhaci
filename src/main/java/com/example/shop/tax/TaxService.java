package com.example.shop.tax;

import java.util.Map;

public class TaxService {

    private static final Map<String, Double> RATES = Map.of(
            "CA", 0.0725,
            "NY", 0.04,
            "TX", 0.0625,
            "OR", 0.0);

    public double taxFor(String state, double amount) {
        Double rate = RATES.get(state);
        if (rate == null) {
            throw new IllegalArgumentException("Unknown state: " + state);
        }
        return Math.round(amount * rate * 100.0) / 100.0;
    }
}

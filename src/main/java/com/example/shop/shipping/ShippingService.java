package com.example.shop.shipping;

public class ShippingService {

    public enum Zone { LOCAL, REGIONAL, INTERNATIONAL }

    /** Flat base fee per zone plus a per-kg charge. Orders over 200 ship free (local/regional). */
    public double shippingCost(Zone zone, double weightKg, double orderTotal) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weight must be > 0");
        }
        if (orderTotal > 200 && zone != Zone.INTERNATIONAL) {
            return 0.0;
        }
        double base;
        double perKg;
        switch (zone) {
            case LOCAL:
                base = 4.0;
                perKg = 0.5;
                break;
            case REGIONAL:
                base = 7.5;
                perKg = 1.0;
                break;
            default:
                base = 20.0;
                perKg = 4.0;
                break;
        }
        return Math.round((base + perKg * weightKg) * 100.0) / 100.0;
    }
}

package com.example.shop.report;

import com.example.shop.loyalty.LoyaltyService;
import com.example.shop.shipping.ShippingService;

/** Builds a one-line order summary; depends on shipping and loyalty only. */
public class ReportService {

    private final ShippingService shipping;
    private final LoyaltyService loyalty;

    public ReportService(ShippingService shipping, LoyaltyService loyalty) {
        this.shipping = shipping;
        this.loyalty = loyalty;
    }

    public String summary(double orderTotal, ShippingService.Zone zone, double weightKg,
                          int lifetimePoints) {
        double ship = shipping.shippingCost(zone, weightKg, orderTotal);
        LoyaltyService.Tier tier = loyalty.tierFor(lifetimePoints);
        int earned = loyalty.pointsEarned(orderTotal, tier);
        return String.format("total=%.2f shipping=%.2f tier=%s points=%d",
                orderTotal, ship, tier, earned);
    }
}

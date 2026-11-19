package com.example.shop.loyalty;

public class LoyaltyService {

    public enum Tier { BRONZE, SILVER, GOLD }

    public Tier tierFor(int lifetimePoints) {
        if (lifetimePoints < 0) {
            throw new IllegalArgumentException("points must be >= 0");
        }
        if (lifetimePoints >= 5000) {
            return Tier.GOLD;
        }
        if (lifetimePoints >= 1000) {
            return Tier.SILVER;
        }
        return Tier.BRONZE;
    }

    /** 1 point per whole currency unit, multiplied by tier. */
    public int pointsEarned(double orderTotal, Tier tier) {
        int base = (int) Math.floor(orderTotal);
        switch (tier) {
            case GOLD:
                return base * 3;
            case SILVER:
                return base * 2;
            default:
                return base;
        }
    }
}

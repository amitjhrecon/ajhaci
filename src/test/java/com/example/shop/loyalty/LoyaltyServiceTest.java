package com.example.shop.loyalty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.shop.TestSupport;
import com.example.shop.loyalty.LoyaltyService.Tier;
import org.junit.jupiter.api.Test;

class LoyaltyServiceTest {

    private final LoyaltyService svc = new LoyaltyService();

    @Test
    void tierBoundaries() {
        TestSupport.simulateWork(1000);
        assertEquals(Tier.BRONZE, svc.tierFor(999));
        assertEquals(Tier.SILVER, svc.tierFor(1000));
        assertEquals(Tier.GOLD, svc.tierFor(5000));
    }

    @Test
    void pointsScaleWithTier() {
        TestSupport.simulateWork(1000);
        assertEquals(100, svc.pointsEarned(100.99, Tier.BRONZE));
        assertEquals(200, svc.pointsEarned(100.99, Tier.SILVER));
        assertEquals(300, svc.pointsEarned(100.99, Tier.GOLD));
    }

    @Test
    void rejectsNegativePoints() {
        assertThrows(IllegalArgumentException.class, () -> svc.tierFor(-1));
    }
}

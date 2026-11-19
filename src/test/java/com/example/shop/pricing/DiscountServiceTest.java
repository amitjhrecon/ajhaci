package com.example.shop.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.shop.TestSupport;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {

    private final DiscountService svc = new DiscountService();

    @Test
    void noDiscountAtOrBelow100() {
        TestSupport.simulateWork(800);
        assertEquals(100.0, svc.applyDiscount(100));
    }

    @Test
    void fivePercentOver100() {
        TestSupport.simulateWork(800);
        assertEquals(190.0, svc.applyDiscount(200));
    }

    @Test
    void tenPercentOver500() {
        TestSupport.simulateWork(800);
        assertEquals(540.0, svc.applyDiscount(600));
    }

    @Test
    void fifteenPercentOver1000() {
        TestSupport.simulateWork(800);
        assertEquals(1700.0, svc.applyDiscount(2000));
    }

    @Test
    void rejectsNegativeAmount() {
        TestSupport.simulateWork(800);
        assertThrows(IllegalArgumentException.class, () -> svc.applyDiscount(-0.01));
    }
}

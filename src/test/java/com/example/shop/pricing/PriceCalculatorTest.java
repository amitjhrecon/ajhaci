package com.example.shop.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.shop.TestSupport;
import org.junit.jupiter.api.Test;

class PriceCalculatorTest {

    private final PriceCalculator calc = new PriceCalculator();

    @Test
    void lineTotalMultipliesPriceByQuantity() {
        TestSupport.simulateWork(500);
        assertEquals(29.97, calc.lineTotal(9.99, 3));
    }

    @Test
    void lineTotalRejectsNegatives() {
        TestSupport.simulateWork(500);
        assertThrows(IllegalArgumentException.class, () -> calc.lineTotal(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> calc.lineTotal(1, -2));
    }

    @Test
    void cartTotalSumsLines() {
        TestSupport.simulateWork(500);
        assertEquals(35.0, calc.cartTotal(new double[]{10, 5}, new int[]{2, 3}));
    }

    @Test
    void cartTotalRejectsMismatchedArrays() {
        TestSupport.simulateWork(500);
        assertThrows(IllegalArgumentException.class,
                () -> calc.cartTotal(new double[]{1}, new int[]{1, 2}));
    }
}

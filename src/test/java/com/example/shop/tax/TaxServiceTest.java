package com.example.shop.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.shop.TestSupport;
import org.junit.jupiter.api.Test;

class TaxServiceTest {

    private final TaxService svc = new TaxService();

    @Test
    void californiaTax() {
        TestSupport.simulateWork(1000);
        assertEquals(7.25, svc.taxFor("CA", 100));
    }

    @Test
    void oregonHasNoSalesTax() {
        TestSupport.simulateWork(1000);
        assertEquals(0.0, svc.taxFor("OR", 100));
    }

    @Test
    void unknownStateIsRejected() {
        TestSupport.simulateWork(1000);
        assertThrows(IllegalArgumentException.class, () -> svc.taxFor("ZZ", 10));
    }
}

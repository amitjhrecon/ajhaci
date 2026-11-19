package com.example.shop.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.shop.TestSupport;
import com.example.shop.shipping.ShippingService.Zone;
import org.junit.jupiter.api.Test;

class ShippingServiceTest {

    private final ShippingService svc = new ShippingService();

    @Test
    void localShipping() {
        TestSupport.simulateWork(1500);
        assertEquals(5.0, svc.shippingCost(Zone.LOCAL, 2, 50));
    }

    @Test
    void regionalShipping() {
        TestSupport.simulateWork(1500);
        assertEquals(10.5, svc.shippingCost(Zone.REGIONAL, 3, 50));
    }

    @Test
    void internationalShipping() {
        TestSupport.simulateWork(1500);
        assertEquals(28.0, svc.shippingCost(Zone.INTERNATIONAL, 2, 500));
    }

    @Test
    void freeShippingOver200Domestic() {
        TestSupport.simulateWork(1500);
        assertEquals(0.0, svc.shippingCost(Zone.REGIONAL, 5, 250));
    }

    @Test
    void rejectsZeroWeight() {
        assertThrows(IllegalArgumentException.class, () -> svc.shippingCost(Zone.LOCAL, 0, 10));
    }
}

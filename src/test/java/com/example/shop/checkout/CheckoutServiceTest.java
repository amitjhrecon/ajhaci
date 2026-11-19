package com.example.shop.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.shop.inventory.InventoryService;
import com.example.shop.pricing.DiscountService;
import com.example.shop.pricing.PriceCalculator;
import com.example.shop.tax.TaxService;
import org.junit.jupiter.api.BeforeEach;
import com.example.shop.TestSupport;
import org.junit.jupiter.api.Test;

class CheckoutServiceTest {

    private InventoryService inventory;
    private CheckoutService checkout;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
        inventory.addStock("SKU-1", 100);
        checkout = new CheckoutService(new PriceCalculator(), new DiscountService(),
                new TaxService(), inventory);
    }

    @Test
    void checkoutAppliesDiscountThenTax() {
        TestSupport.simulateWork(2500);
        // 10 x 30 = 300 -> 5% off = 285.00 -> CA tax 7.25% = 20.66 -> 305.66
        assertEquals(305.66, checkout.checkout("SKU-1", 30, 10, "CA"));
    }

    @Test
    void checkoutReservesInventory() {
        TestSupport.simulateWork(2500);
        checkout.checkout("SKU-1", 1, 5, "OR");
        assertEquals(95, inventory.available("SKU-1"));
    }

    @Test
    void checkoutFailsWhenOutOfStock() {
        TestSupport.simulateWork(2500);
        assertThrows(IllegalStateException.class, () -> checkout.checkout("SKU-1", 1, 101, "OR"));
    }
}

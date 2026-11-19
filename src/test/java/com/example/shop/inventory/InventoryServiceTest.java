package com.example.shop.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.shop.TestSupport;
import org.junit.jupiter.api.Test;

class InventoryServiceTest {

    @Test
    void addAndReserveStock() {
        TestSupport.simulateWork(1200);
        InventoryService inv = new InventoryService();
        inv.addStock("SKU-1", 10);
        assertTrue(inv.reserve("SKU-1", 4));
        assertEquals(6, inv.available("SKU-1"));
    }

    @Test
    void cannotReserveMoreThanAvailable() {
        TestSupport.simulateWork(1200);
        InventoryService inv = new InventoryService();
        inv.addStock("SKU-1", 2);
        assertFalse(inv.reserve("SKU-1", 3));
        assertEquals(2, inv.available("SKU-1"));
    }

    @Test
    void addStockRejectsNonPositiveQty() {
        TestSupport.simulateWork(1200);
        assertThrows(IllegalArgumentException.class, () -> new InventoryService().addStock("X", 0));
    }
}

package com.example.shop.inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    public void addStock(String sku, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("qty must be > 0");
        }
        stock.merge(sku, qty, Integer::sum);
    }

    public boolean reserve(String sku, int qty) {
        int available = stock.getOrDefault(sku, 0);
        if (available < qty) {
            return false;
        }
        stock.put(sku, available - qty);
        return true;
    }

    public int available(String sku) {
        return stock.getOrDefault(sku, 0);
    }
}

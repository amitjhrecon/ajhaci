package com.example.shop.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.shop.TestSupport;
import com.example.shop.loyalty.LoyaltyService;
import com.example.shop.shipping.ShippingService;
import com.example.shop.shipping.ShippingService.Zone;
import org.junit.jupiter.api.Test;

class ReportServiceTest {

    private final ReportService report =
            new ReportService(new ShippingService(), new LoyaltyService());

    @Test
    void summaryForBronzeCustomer() {
        TestSupport.simulateWork(2000);
        assertEquals("total=50.00 shipping=5.00 tier=BRONZE points=50",
                report.summary(50, Zone.LOCAL, 2, 10));
    }

    @Test
    void summaryForGoldCustomerWithFreeShipping() {
        TestSupport.simulateWork(2000);
        assertEquals("total=300.00 shipping=0.00 tier=GOLD points=900",
                report.summary(300, Zone.REGIONAL, 4, 6000));
    }
}

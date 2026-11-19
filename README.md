# ti-demo-app

Small Java/Gradle/JUnit 5 shop app used to demo Harness CI **Test Intelligence (TI)** and
**test splitting** (parallel test execution).

```
pricing/PriceCalculator    <- PriceCalculatorTest, CheckoutServiceTest
pricing/DiscountService    <- DiscountServiceTest, CheckoutServiceTest
tax/TaxService             <- TaxServiceTest,       CheckoutServiceTest
inventory/InventoryService <- InventoryServiceTest, CheckoutServiceTest
checkout/CheckoutService   <- CheckoutServiceTest only
shipping/ShippingService   <- ShippingServiceTest,  ReportServiceTest
loyalty/LoyaltyService     <- LoyaltyServiceTest,   ReportServiceTest
report/ReportService       <- ReportServiceTest only
```

There are two independent "clusters" (checkout and report), so a change in one cluster
leaves the other cluster's tests unselected.

Tests call `TestSupport.simulateWork(ms)` so each class has a different, realistic duration
(about 0.5-2.5 s per test, ~45 s total serially). That gives class-timing-based splitting
something to balance. Run fast locally with `gradle test -Dtest.work.ms=0`
(the build forwards that property to the test JVM).

Optional in-JVM parallelism (not the Harness mechanism): `gradle test -PtestForks=2`.

## Demo script

1. **Baseline** - run the pipeline on `main` via a push trigger. TI has no call graph yet, so
   all 8 test classes run, split across 3 parallel instances. Class timings are recorded.
2. **Re-run unchanged / small change** - the split is now balanced by recorded class timings
   rather than test count. Compare the three instances' durations.
3. **Change a leaf** - edit `TaxService` on a branch, open a PR to `main`, run on the PR.
   Only `TaxServiceTest` + `CheckoutServiceTest` are selected, then split across instances.
4. **Change the other cluster** - edit `ShippingService`: only `ShippingServiceTest` and
   `ReportServiceTest` run.
5. **Change a test only** - edit `LoyaltyServiceTest`: only that class runs.
6. **Change `build.gradle`** - TI cannot reason about it and falls back to running everything.
7. Open the **Tests** tab: selected vs. skipped tests, time saved, per-instance results.
8. Set `runOnlySelectedTests: false` at run time to compare "all tests, split 3 ways" with
   "TI-selected tests, split 3 ways".

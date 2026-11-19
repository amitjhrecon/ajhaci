package com.example.shop;

/**
 * Simulates the slow tests of a real suite so that test splitting by class timing has
 * something to balance. Override with -Dtest.work.ms=0 (or JAVA_TOOL_OPTIONS) for fast local runs.
 */
public final class TestSupport {

    private static final long SCALE_MS = Long.getLong("test.work.ms", -1);

    private TestSupport() { }

    public static void simulateWork(long ms) {
        long actual = SCALE_MS >= 0 ? SCALE_MS : ms;
        try {
            Thread.sleep(actual);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

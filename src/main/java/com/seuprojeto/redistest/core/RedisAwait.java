package com.seuprojeto.redistest.core;

public class RedisAwait {
    public static void until(boolean condition, long timeoutMillis) {
        long start = System.currentTimeMillis();
        while (!condition) {
            if (System.currentTimeMillis() - start > timeoutMillis) {
                throw new RuntimeException("Timeout waiting condition");
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}


package com.seuprojeto.redistest.bucket.model;

import org.testng.Assert;

public class BucketConsumptionResult {

    private final BucketState before;
    private final BucketState after;

    public BucketConsumptionResult(BucketState before, BucketState after) {
        this.before = before;
        this.after = after;
    }

    public BucketState getBefore() { return before; }
    public BucketState getAfter() { return after; }

    public void assertConsumedExactly(long amount) {
        Assert.assertNotNull(before.getNumericValue(), "Valor anterior não é numérico");
        Assert.assertNotNull(after.getNumericValue(), "Valor posterior não é numérico");
        Assert.assertEquals(
                after.getNumericValue().longValue(),
                before.getNumericValue() - amount,
                "Consumo inesperado do bucket"
        );
    }
}

package com.seuprojeto.redistest.bucket;

import com.seuprojeto.redistest.bucket.model.BucketConsumptionResult;
import com.seuprojeto.redistest.bucket.model.BucketState;
import com.seuprojeto.redistest.bucket.strategy.HashBucketStrategy;
import com.seuprojeto.redistest.bucket.strategy.StringBucketStrategy;
import com.seuprojeto.redistest.core.RedisAwait;
import com.seuprojeto.redistest.connection.RedisSession;

public class BucketFacade {

    private final RedisSession session;
    private final String key;
    private BucketStructure structure;

    public BucketFacade(RedisSession session, String key) {
        this.session = session;
        this.key = key;
    }

    public BucketFacade using(BucketStructure structure) {
        this.structure = structure;
        return this;
    }

    public BucketState read() {
        ensureStructure();
        if (structure != BucketStructure.STRING) {
            throw new IllegalStateException("Use read(field) para estruturas não-string.");
        }
        return new StringBucketStrategy(session).read(key);
    }

    public BucketState read(String field) {
        ensureStructure();
        if (structure != BucketStructure.HASH) {
            throw new IllegalStateException("read(field) suportado neste exemplo para HASH.");
        }
        return new HashBucketStrategy(session).read(key, field);
    }

    public BucketConsumptionResult verifyConsumption(String field, long expectedConsumed, Runnable action) {
        BucketState before = read(field);
        action.run();

        BucketState after = RedisAwait.until(
                () -> read(field),
                state -> state.getNumericValue() != null
                        && before.getNumericValue() != null
                        && state.getNumericValue().longValue() == before.getNumericValue().longValue() - expectedConsumed,
                10,
                200
        );

        return new BucketConsumptionResult(before, after);
    }

    private void ensureStructure() {
        if (structure == null) {
            throw new IllegalStateException("BucketStructure deve ser definido com using(...).");
        }
    }
}

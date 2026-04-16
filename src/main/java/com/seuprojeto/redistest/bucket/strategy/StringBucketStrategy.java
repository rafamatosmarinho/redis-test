package com.seuprojeto.redistest.bucket.strategy;

import com.seuprojeto.redistest.bucket.model.BucketState;
import com.seuprojeto.redistest.connection.RedisSession;

public class StringBucketStrategy {
    private final RedisSession session;

    public StringBucketStrategy(RedisSession session) {
        this.session = session;
    }

    public BucketState read(String key) {
        String value = session.get(key);
        Long numeric = parseLong(value);
        return new BucketState(key, null, value, numeric);
    }

    public void reset(String key, String value) {
        session.set(key, value);
    }

    private Long parseLong(String value) {
        if (value == null) return null;
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

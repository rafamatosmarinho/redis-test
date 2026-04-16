package com.seuprojeto.redistest.bucket.strategy;

import com.seuprojeto.redistest.bucket.model.BucketState;
import com.seuprojeto.redistest.connection.RedisSession;

public class HashBucketStrategy {

    private final RedisSession session;

    public HashBucketStrategy(RedisSession session) {
        this.session = session;
    }

    public BucketState read(String key, String field) {
        String value = session.hget(key, field);
        Long numeric = parseLong(value);
        return new BucketState(key, field, value, numeric);
    }

    public void reset(String key, String field, String value) {
        session.hset(key, field, value);
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

package com.seuprojeto.redistest.core;

import com.seuprojeto.redistest.config.RedisTestConfig;
import com.seuprojeto.redistest.connection.RedisConnectionFactory;
import com.seuprojeto.redistest.connection.RedisSession;

public class RedisTestClient implements AutoCloseable {

    private final RedisSession session;
    private final RedisResetService resetService;
    private final RedisKeyOps keyOps;

    private RedisTestClient(RedisSession session) {
        this.session = session;
        this.resetService = new RedisResetService(session);
        this.keyOps = new RedisKeyOps(session);
    }

    public static RedisTestClient create(RedisTestConfig config) {
        return new RedisTestClient(RedisConnectionFactory.create(config));
    }

    public com.seuprojeto.redistest.bucket.BucketFacade bucket(String key) {
        return new com.seuprojeto.redistest.bucket.BucketFacade(session, key);
    }

    public RedisResetService reset() {
        return resetService;
    }

    public RedisKeyOps keys() {
        return keyOps;
    }

    @Override
    public void close() {
        session.close();
    }
}

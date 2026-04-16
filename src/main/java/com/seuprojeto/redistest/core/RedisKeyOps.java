package com.seuprojeto.redistest.core;

import com.seuprojeto.redistest.connection.RedisSession;
import java.util.Set;

public class RedisKeyOps {
    private final RedisSession session;

    public RedisKeyOps(RedisSession session) {
        this.session = session;
    }

    public boolean exists(String key) {
        return session.exists(key);
    }

    public long del(String... keys) {
        return session.del(keys);
    }

    public Set<String> keys(String pattern) {
        Set<String> ks = session.keys(pattern);
        return ks != null ? ks : Set.of();
    }
}

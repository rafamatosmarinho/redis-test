package com.seuprojeto.redistest.connection;

import redis.clients.jedis.JedisPooled;
import java.util.Map;
import java.util.Set;

public class RedisSession {
    private final JedisPooled client;

    public RedisSession(JedisPooled client) {
        this.client = client;
    }

    public String get(String key) {
        return client.get(key);
    }

    public void set(String key, String value) {
        client.set(key, value);
    }

    public boolean exists(String key) {
        return client.exists(key);
    }

    public long del(String... keys) {
        return client.del(keys);
    }

    public Set<String> keys(String pattern) {
        Set<String> ks = client.keys(pattern);
        return ks != null ? ks : Set.of();
    }

    public void flushDB() {
        client.flushDB();
    }

    public void close() {
        client.close();
    }

    // New hash operations to avoid exposing native client
    public String hget(String key, String field) {
        return client.hget(key, field);
    }

    public void hset(String key, String field, String value) {
        client.hset(key, field, value);
    }

    public void hset(String key, Map<String, String> map) {
        if (map == null || map.isEmpty()) return;
        client.hset(key, map);
    }

    // Keep access to native client if strictly necessary
    public JedisPooled getNativeClient() {
        return client;
    }
}

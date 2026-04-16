package com.seuprojeto.redistest.connection;

import com.seuprojeto.redistest.config.RedisTestConfig;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.JedisPooled;

public final class RedisConnectionFactory {

    private RedisConnectionFactory() {
    }

    public static RedisSession create(RedisTestConfig config) {
        DefaultJedisClientConfig clientConfig = DefaultJedisClientConfig.builder()
                .user(config.getUsername())
                .password(config.getPassword())
                .database(config.getDatabase())
                .ssl(config.isSsl())
                .timeoutMillis(config.getTimeoutMillis())
                .build();

        JedisPooled jedis = new JedisPooled(config.getHost(), config.getPort(), clientConfig);
        return new RedisSession(jedis);
    }
}

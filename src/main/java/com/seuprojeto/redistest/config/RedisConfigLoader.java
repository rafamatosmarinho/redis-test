package com.seuprojeto.redistest.config;

public class RedisConfigLoader {
    public static RedisTestConfig load() {
        String host = getEnvOrProp("REDIS_HOST", "localhost");
        int port = parseInt(getEnvOrProp("REDIS_PORT", "6379"), 6379);
        String username = getEnvOrProp("REDIS_USERNAME", null);
        String password = getEnvOrProp("REDIS_PASSWORD", null);
        int database = parseInt(getEnvOrProp("REDIS_DATABASE", "0"), 0);
        boolean ssl = parseBoolean(getEnvOrProp("REDIS_SSL", "false"));
        int timeout = parseInt(getEnvOrProp("REDIS_TIMEOUT_MS", "2000"), 2000);

        RedisTestConfig.Builder b = RedisTestConfig.builder()
                .host(host)
                .port(port)
                .database(database)
                .ssl(ssl)
                .timeoutMillis(timeout);

        if (username != null && !username.isEmpty()) b.username(username);
        if (password != null && !password.isEmpty()) b.password(password);

        return b.build();
    }

    private static String getEnvOrProp(String key, String defaultValue) {
        String v = System.getenv(key);
        if (v == null) v = System.getProperty(key);
        return v != null ? v : defaultValue;
    }

    private static int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    private static boolean parseBoolean(String s) {
        if (s == null) return false;
        return "true".equalsIgnoreCase(s) || "1".equals(s);
    }
}

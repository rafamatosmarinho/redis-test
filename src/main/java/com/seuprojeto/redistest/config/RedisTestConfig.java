package com.seuprojeto.redistest.config;

public class RedisTestConfig {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final int database;
    private final boolean ssl;
    private final int timeoutMillis;

    private RedisTestConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.database = builder.database;
        this.ssl = builder.ssl;
        this.timeoutMillis = builder.timeoutMillis;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getDatabase() { return database; }
    public boolean isSsl() { return ssl; }
    public int getTimeoutMillis() { return timeoutMillis; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String host = "localhost";
        private int port = 6379;
        private String username;
        private String password;
        private int database = 0;
        private boolean ssl = false;
        private int timeoutMillis = 2000;

        public Builder host(String host) { this.host = host; return this; }
        public Builder port(int port) { this.port = port; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder database(int database) { this.database = database; return this; }
        public Builder ssl(boolean ssl) { this.ssl = ssl; return this; }
        public Builder timeoutMillis(int timeoutMillis) { this.timeoutMillis = timeoutMillis; return this; }

        public RedisTestConfig build() {
            return new RedisTestConfig(this);
        }
    }
}

package com.seuprojeto.redistest.core;

import com.seuprojeto.redistest.connection.RedisSession;
import java.util.HashSet;
import java.util.Set;

public class RedisResetService {
    private final RedisSession session;

    public RedisResetService(RedisSession session) {
        this.session = session;
    }

    /**
     * Remove todas as chaves no banco selecionado.
     */
    public void resetAll() {
        session.flushDB();
    }

    /**
     * Deleta chaves que correspondam ao padrão (usa KEYS internamente - adequado para testes).
     */
    public long deleteByPattern(String pattern) {
        Set<String> keys = session.keys(pattern);
        if (keys == null || keys.isEmpty()) return 0L;
        String[] arr = keys.toArray(new String[0]);
        return session.del(arr);
    }

    /**
     * Lista chaves que correspondam ao padrão.
     */
    public Set<String> listKeys(String pattern) {
        Set<String> keys = session.keys(pattern);
        return keys != null ? keys : new HashSet<>();
    }
}

package com.seuprojeto.redistest.bucket.model;

public class BucketState {
    private final String key;
    private final String field;
    private final String rawValue;
    private final Long numericValue;

    public BucketState(String key, String field, String rawValue, Long numericValue) {
        this.key = key;
        this.field = field;
        this.rawValue = rawValue;
        this.numericValue = numericValue;
    }

    public String getKey() { return key; }
    public String getField() { return field; }
    public String getRawValue() { return rawValue; }
    public Long getNumericValue() { return numericValue; }
}

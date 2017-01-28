package com.boris.reservations.security;

public interface TokenExtractor {
    public String extract(String payload);
}

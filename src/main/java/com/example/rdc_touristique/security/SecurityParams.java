package com.example.rdc_touristique.security;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String SECRET = "Clio_Nigala_tShishi*11";
    public static final long EXPIRATION = 10*24*3600*1000; //10 jours
    public static final String HEADER_PREFIX = "bearer ";
}

package com.example.rdc_touristique.security;

public interface SecurityParams {
    public static final String secret = "Clio_Nigala_tShishi*11";
    public static final long EXPIRATION_TIME = 10*24*3600*1000; //10 jours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}

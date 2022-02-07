package com.example.rdc_touristique.security.config;


public interface constParam {
    String JWT_NAME = "Authorization";
    String SECRET = "ClioNigalatShishi11";
    long EXPIRATION = 10*24*3600*1000; //10 jours
    String HEADER_PREFIX = "Bearer ";
}

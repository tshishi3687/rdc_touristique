package com.example.rdc_touristique.security;

import antlr.BaseAST;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Header", "Origin, Accept, X-Resquested-With, Content-Type, Access-Control-Resquest-Method, Access-Control-Resquest-Headers,authorization");
//        response.addHeader("Access-Control-Expose-Header", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
//

        System.out.println("request " + request);

        String jwtToken = request.getHeader(SecurityParams.HEADER_STRING);
        System.out.println("token = " + jwtToken);
        if (jwtToken==null || !jwtToken.startsWith(SecurityParams.TOKEN_PREFIX)){
            filterChain.doFilter(request, response);
            return;
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.secret)).build();
        String jwt = jwtToken.substring(SecurityParams.TOKEN_PREFIX.length());
        DecodedJWT decodedJWT = verifier.verify(jwt);
        System.out.println("JWT = " + jwt);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        System.out.println("username = " + username);
        System.out.println("roles = " + roles);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(rn ->{
            authorities.add(new SimpleGrantedAuthority(rn));
        });

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username,
                null,  authorities);
        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);
    }
}

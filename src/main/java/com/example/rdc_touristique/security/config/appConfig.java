//package com.example.rdc_touristique.security;
//import org.apache.catalina.security.SecurityConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import uk.co.ticklethepanda.spring.auth.jwt.JwtAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//public class appConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder);
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/ville/*", "/province/*", "/service/*", "/type_bien/*", "/type/*")
//                .hasAuthority("Admin");
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/personne/*", "/ville/*", "/province/*", "/service/*", "/type_bien/*", "/type/*")
//                .hasAuthority("Admin");
//        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/personne/*", "/ville/*", "/province/*", "/service/*", "/type_bien/*", "/type/*")
//                .hasAuthority("Admin");
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
//        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
////
////
////private final static String user = "user";
////    private final static String code = "root";
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth.inMemoryAuthentication()
////                .withUser(user).password(encoder().encode(code))
////                .authorities("ROLE_USER");
////
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.cors().and().csrf().disable()
////                .authorizeRequests()
////                .anyRequest().permitAll()
////                .and()
////                .httpBasic();
////    }
////
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.addAllowedOrigin("http://localhost:4200");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addExposedHeader("Authorization");
//        return request -> corsConfiguration;
//    }
//
//    @Bean
//    public PasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//}

package com.example.rdc_touristique.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
         securedEnabled = true,
         jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.requiresChannel()
//                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
//                .requiresSecure();

        http.cors().and().csrf().disable()

                .authorizeRequests().antMatchers("/personne/user", "/personne/creat", "/personne/email", "/personne/change_passe", "/personne/mdp_modif", "/personne/activation_compte").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/bien/count", "/bien/allBiens", "/province", "/ville", "/typebien", "/personne/nbcompte").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/ville/*", "/province/*", "/service/*", "/typebien/*", "/type/*", "/personne")
                .hasAuthority("Admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/ville/", "/province/", "/service/", "/typebien/", "/type/", "/personne")
                .hasAuthority("Admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/service/*", "/type_bien/*", "/type/*", "/personne")
                .hasAuthority("Admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/service/*", "/type_bien/*", "/type/*", "/personne")
                .hasAuthority("Admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/service/", "/type_bien/", "/type/", "/personne")
                .hasAuthority("Admin")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader(constParam.JWT_NAME);
        corsConfiguration.applyPermitDefaultValues();
        return request -> corsConfiguration;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

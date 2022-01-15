package com.example.rdc_touristique.security;

import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {

    private final Integer id;
    private final String prenom;
    private final String nom;
    private final LocalDate ddn;
    private final String password;
    private final boolean active;
    private final List<GrantedAuthority> authorities;

    public MyUserPrincipal(Personne user){
        this.id = user.getId();
        this.prenom = user.getPrenom();
        this.ddn = user.getDdn();
        this.nom = user.getNom();
        this.password = (LocalDateTime.now()) + "_tu croyais quoi?";
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoll().getNomRoll().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDdn() {
        return ddn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nom;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

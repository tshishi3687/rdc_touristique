package com.example.rdc_touristique.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.example.rdc_touristique.data_access.entity.ContactPersonne;
import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ContactUserRepository contactUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ContactPersonne> entity = contactUserRepository.findByEmail(email);
        if (entity.isPresent()) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(entity.get().getAppartienA().getMdp().getMdp()));
            return new User(entity.get().getEmail(), entity.get().getAppartienA().getMdp().getMdp(),
                    authorities);
        }
        return null;
    }

}

//package com.example.rdc_touristique.security.config.conf.sercurity;
//
//import com.example.rdc_touristique.data_access.entity.ContactPersonne;
//import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    ContactUserRepository contactUserRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<ContactPersonne> entity = contactUserRepository.findByEmail(username);
//        if (entity.isPresent()) {
//            return UserDetailsImpl.build(entity.get().getAppartienA());
//
//        }
//        return null;
//    }
//}

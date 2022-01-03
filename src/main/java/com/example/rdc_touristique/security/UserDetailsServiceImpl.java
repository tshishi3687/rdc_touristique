package com.example.rdc_touristique.security;

import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.PassWord;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
import com.example.rdc_touristique.data_access.repository.PassWordRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.PersonneSimpleExisteExeption;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ContactUserRepository contactUserRepository;
    @Autowired
    private PassWordRepository passWordRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ContactUser> contactUser = contactUserRepository.findByEmail(username);

//        if (contactUser.isPresent()){
//            List<PassWord> passWord = passWordRepository.findAllByAppartienA(contactUser.get().getAppartienA());
//
//            System.out.println("je suis la  1");
//            for (PassWord word : passWord) {
//                if (word.isMode()){
//
//                    System.out.println("je suis la  2");
//
//                    Collection<GrantedAuthority> authorities = new ArrayList<>();
//                    authorities.add(new SimpleGrantedAuthority(word.getAppartienA().getRoll().getNomRoll()));
//                    System.out.println(new User(contactUser.get().getEmail(), word.getMdp(), authorities));
//                    System.out.println("**************************************");
//                    return new User(contactUser.get().getEmail(), word.getMdp(), authorities);
//                }
//            }
//        }
        return null;
    }
}

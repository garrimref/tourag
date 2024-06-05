package com.reltour.tourag.security;

import com.reltour.tourag.domain.User;
import com.reltour.tourag.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetails(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            return user;
        }
        else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
package com.example.Socialnetwork.bo;

import com.example.Socialnetwork.db.UserDB;
import com.example.Socialnetwork.db.UserRepository;
import com.example.Socialnetwork.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDB> userDbOpt = userRepository.getUserByUsername(username);
        System.out.println(userDbOpt.get().getFirstname());
        System.out.println(userDbOpt.get().getPassword());
        return userDbOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}

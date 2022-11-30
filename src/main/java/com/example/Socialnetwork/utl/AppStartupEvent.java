package com.example.Socialnetwork.utl;

import com.example.Socialnetwork.db.UserRepository;
import com.example.Socialnetwork.db.UserDB;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    public AppStartupEvent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<UserDB> theUsers = this.userRepository.findAll();
        theUsers.forEach(System.out::println);
    }
}
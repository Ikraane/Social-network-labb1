package com.example.Socialnetwork.bo;

import com.example.Socialnetwork.db.UserDB;
import com.example.Socialnetwork.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) throws IllegalArgumentException{

        if (username == null) throw new IllegalArgumentException();
        else {
            User user = new User();
            UserDB userDB = userRepository.getUserByUsername(username).get();
            if (userDB == null) throw new IllegalArgumentException();
            else {
                user.setUsername(userDB.getUsername());
                user.setPassword(userDB.getPassword());
                user.setFirstname(userDB.getFirstname());
                user.setLastname(userDB.getLastname());

                return user;
            }

        }

    }

    @Override
    public String saveUser(User user) {
        UserDB userDB = new UserDB();
        userDB.setUsername(user.getUsername());
        userDB.setPassword(user.getPassword());
        userDB.setFirstname(user.getFirstname());
        userDB.setLastname(user.getLastname());

        return user.getUsername();
    }

    @Override
    public boolean checkUserLogin(User user) {
        UserDB userDB = userRepository.getUserByUsername(user.getUsername()).get();
        if(userDB == null) {
            return false;
        }
        else if(userDB.getPassword().equals(user.getPassword())){
            return true;
        }
        else {
            return false;
        }
    }
}

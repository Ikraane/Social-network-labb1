package com.example.Socialnetwork.bo;

import com.example.Socialnetwork.db.UserDB;

public interface IUserService {
    public User getUserByUsername(String username);
    public UserDB saveUser(User user);
    public boolean checkUserLogin(User user);
}

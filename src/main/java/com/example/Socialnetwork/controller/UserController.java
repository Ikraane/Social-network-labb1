package com.example.Socialnetwork.controller;

import com.example.Socialnetwork.bo.IUserService;
import com.example.Socialnetwork.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user) {
        iUserService.saveUser(user);
        return "New user is added";
    }

    @GetMapping("/getUser")
    public User getUserByUsername(@RequestParam(value = "username", required = false) String username)
    {
        return iUserService.getUserByUsername(username);
    }

    @PostMapping("/checkUserLogin")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean checkUserLogin(@RequestBody User user) {
        return iUserService.checkUserLogin(user);
    }



}

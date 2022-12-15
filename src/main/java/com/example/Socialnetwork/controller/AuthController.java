package com.example.Socialnetwork.controller;

import com.example.Socialnetwork.bo.AuthCredentialsRequest;
import com.example.Socialnetwork.db.UserDB;
import com.example.Socialnetwork.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login (@RequestBody AuthCredentialsRequest req) {

        try {
            System.out.println("in controller" + req.getPassword());
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    req.getUsername(), req.getPassword()
                            )
                    );

            UserDB userDb = (UserDB) authentication.getPrincipal();
            userDb.setPassword(null);
            /*UserDto userDto = new UserDto();
            userDto.setUsername(userDb.getUsername());
            userDto.setFull_name(userDb.getFull_name());
             */

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(userDb)
                    )
                    .body(userDb);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

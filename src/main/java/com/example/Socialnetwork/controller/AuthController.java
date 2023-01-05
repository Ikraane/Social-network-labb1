package com.example.Socialnetwork.controller;

import com.example.Socialnetwork.bo.AuthCredentialsRequest;
import com.example.Socialnetwork.bo.IUserService;
import com.example.Socialnetwork.bo.User;
import com.example.Socialnetwork.db.UserDB;
import com.example.Socialnetwork.db.UserRepository;
import com.example.Socialnetwork.util.CustomPasswordEncoder;
import com.example.Socialnetwork.util.JwtResponse;
import com.example.Socialnetwork.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    @Autowired
    private final IUserService iUserService;

    public AuthController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

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

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDB userDb = (UserDB) authentication.getPrincipal();
            userDb.setPassword(null);
            /*UserDto userDto = new UserDto();
            userDto.setUsername(userDb.getUsername());
            userDto.setFull_name(userDb.getFull_name());
             */

            String jwt = jwtUtil.generateToken(userDb);
            List<String> roles = userDb.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, userDb.getUsername(), roles));
                    /*.header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(userDb)
                    )
                    .body(userDb);*/
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody User user) throws FileAlreadyExistsException {
        if (this.userRepository.getUserByUsername(user.getUsername()).isPresent())
            throw new FileAlreadyExistsException("User already exist, try another username");
        String encodedPassword = passwordEncoder.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return this.iUserService.saveUser(user);
    }
}

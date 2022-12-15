package com.example.Socialnetwork.db;

import com.example.Socialnetwork.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDB, String> {
    Optional<UserDB> getUserByUsername(String username);
}

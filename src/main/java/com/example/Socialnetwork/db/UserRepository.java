package com.example.Socialnetwork.db;

import com.example.Socialnetwork.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDB, String> {
    UserDB getUserByUsername(String username);
}

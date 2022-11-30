package com.example.Socialnetwork.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageDB, String> {
    public List<MessageDB> findMessageByReceiver(String receiver);
}

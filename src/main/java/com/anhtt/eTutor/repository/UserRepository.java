package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Account, UUID> {
    Account findByEmail(String email);
    Boolean existsByEmail(String email);
    
    List<Account> findByisDeletedFalse();
}
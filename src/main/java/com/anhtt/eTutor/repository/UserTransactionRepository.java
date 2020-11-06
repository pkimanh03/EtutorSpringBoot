package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, UUID> {
    List<UserTransaction> findAllByAccount(Account account);
    
    List<UserTransaction> findByisDeletedFalse();
}

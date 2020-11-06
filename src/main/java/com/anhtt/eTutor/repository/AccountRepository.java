package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findAccountById(UUID id);
    Account findAccountByEmail(String email);
    Account findAccountByTutor(Tutor tutor);
    Account findAccountByTutor_Id(UUID tutorId);
    List<Account> findByisDeletedFalse();
}
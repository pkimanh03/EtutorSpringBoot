package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * TransactionTypeRepository
 */
public interface TransactionTypeRepository extends JpaRepository<TransactionType, UUID> {
    TransactionType findByName(String name);

    List<TransactionType> findByisDeletedFalse();
}
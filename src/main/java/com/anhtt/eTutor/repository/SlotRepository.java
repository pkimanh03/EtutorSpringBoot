package com.anhtt.eTutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhtt.eTutor.model.Slot;

import java.util.List;
import java.util.UUID;

/**
 * SlotRepository
 */
@Repository
public interface SlotRepository extends JpaRepository<Slot, UUID>{

	List<Slot> findByisDeletedFalse();
}
package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, UUID> {
    AvailableTime findByTutor_Id(UUID tutorId);
}

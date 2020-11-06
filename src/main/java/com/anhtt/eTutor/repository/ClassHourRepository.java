package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhtt.eTutor.model.ClassHour;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassHourRepository extends JpaRepository<ClassHour, UUID>{
    List<ClassHour> findClassHoursByRegistration(Registration registration);
    
    List<ClassHour> findByisDeletedFalse();

}
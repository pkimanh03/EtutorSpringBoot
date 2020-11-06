package com.anhtt.eTutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhtt.eTutor.model.Attendance;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID>{
    Attendance findByClassHour_Id(UUID classhourId);

    Attendance findByStatus(String status);
    
    List<Attendance> findByisDeleteFalse();
}
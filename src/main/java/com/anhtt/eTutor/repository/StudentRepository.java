package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.dto.StudentShowDTO;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * StudentRepository
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Student findByStudentAccount(Account account);

    @Query("SELECT new com.anhtt.eTutor.model.Student(a.fullname, a.age, a.address, a.phoneNumber, a.favoriteMajors, a.isDelete) FROM Student a WHERE a.id = :id")
    Student getStudentByID(@Param("id") UUID id);

    List<Student> findByisDeleteFalse();

    Student findStudentByStudentAccount_Email(String studentEmail);
}
package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Course;
import com.anhtt.eTutor.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.anhtt.eTutor.model.Registration;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends  JpaRepository<Registration, UUID>{
	
	@Query("SELECT r FROM Registration r WHERE r.id = :uuid")
	Registration findRegis(@PathVariable UUID uuid);

	Registration findRegistrationByCourseAndStudent(Course course, Student student);

	List<Registration> findRegistrationsByStudent(Student student);
	
	List<Registration> findByisDeletedFalse();

	List<Registration> findRegistrationsByCourse_TutorTutorAccount(Account tutorAccount);
	List<Registration> findRegistrationsByStudent_StudentAccount(Account studentAccount);

	@Query(value = "SELECT r FROM Registration r WHERE create_at LIKE :createAt")
	List<Registration> findByCreatedAt(@Param("createAt") String createAt);
}
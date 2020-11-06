package com.anhtt.eTutor.repository;

import java.util.List;
import java.util.UUID;

import com.anhtt.eTutor.dto.MajorsDTO;
import com.anhtt.eTutor.model.Majors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * MajorRepository
 */

@Repository
public interface MajorRepository extends JpaRepository<Majors, UUID> {

	// @Transactional
	// @Query(value = "SELECT m.name, m.description FROM Majors m, Registration r,
	// Course c WHERE m.id = c.major_id AND c.id = r.course_id")
	// List<MajorsDTO> getMajorsMostRegis();

	@Query("SELECT new com.anhtt.eTutor.dto.MajorsDTO(m.id,m.name, m.description) FROM Majors m")
	List<MajorsDTO> getAllMajors();

	@Query(value = "SELECT Top 3 * FROM majors m WHERE is_deleted = 0", nativeQuery = true)
	List<Majors> findTop3Majors();

	@Query(value = "SELECT majors.name, majors.description, COUNT(registration.id)\r\n"
			+ "FROM registration, course, majors\r\n"
			+ "WHERE majors.id = course.major_id AND course.id = registration.course_id \r\n"
			+ "GROUP BY majors.name, majors.description", nativeQuery = true)
	MajorsDTO getMajorLearnMost();

	List<Majors> findByisDeletedFalse();

	@Query(value = "SELECT Top 5 * FROM majors m WHERE is_deleted = 0", nativeQuery = true)
	List<Majors> findTop5Majors();
}
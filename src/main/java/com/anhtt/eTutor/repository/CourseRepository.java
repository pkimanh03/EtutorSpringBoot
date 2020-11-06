package com.anhtt.eTutor.repository;

import java.util.List;
import java.util.UUID;

import com.anhtt.eTutor.model.Course;
import com.anhtt.eTutor.model.Majors;
import com.anhtt.eTutor.model.Tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CourseRepository
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findCourseById(@Param("id") UUID id);

	List<Course> findCourseByMajorsId(UUID id);

	@Query(value = "SELECT * FROM course c WHERE c.name LIKE %:courseName%", nativeQuery = true)
	List<Course> findCoursesByNameLike(@Param("courseName") String courseName);

	// @Query(value = "SELECT * FROM course c WHERE c.name LIKE %:courseName% AND tutor_id LIKE %:tutor_id%", nativeQuery = true)
	// List<Course> findCoursesByNameAndByTutorId(@Param("courseName") String courseName,
	// 		@Param("tutor_id") UUID tutor_id);

	Course findCourseByTutor(Tutor tutor);

	List<Course> findCoursesByTutor_Id(UUID tutorId);

	List<Course> findTop5Bymajors(Majors m);

	List<Course> findByisDeletedFalse();

	@Query(value = "SELECT * FROM course, majors WHERE course.major_id = majors.id AND course.major_id like %:id%", nativeQuery = true)
	List<Course> findByMajorID(@Param("id") UUID id);

	@Query(value = "SELECT TOP 20 * FROM course", nativeQuery = true)
	List<Course> findTop20Course();

	@Query(value = "SELECT Top 5 * FROM course c WHERE is_deleted = 0", nativeQuery = true)
	List<Course> findTop5Courses();

	@Query(value = "SELECT DISTINCT TOP 5 c.name FROM course c WHERE c.major_id LIKE %:majorsId%", nativeQuery = true)
	List<String> find5DistinctCourseNameByMajors(@Param("majorsId") UUID majorsId);

	@Query(value = "SELECT TOP 1 * FROM course c WHERE c.name LIKE %:name%", nativeQuery = true)
	Course findCourseByName(@Param("name") String name);
}
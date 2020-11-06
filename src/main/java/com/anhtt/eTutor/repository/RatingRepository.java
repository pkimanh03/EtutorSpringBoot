package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * RatingRepository
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
	
	List<Rating> findByisDeletedFalse();

}
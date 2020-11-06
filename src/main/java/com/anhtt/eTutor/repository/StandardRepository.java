package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.dto.StandardInsertDTO;
import com.anhtt.eTutor.model.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * StandardRepository
 */
@Repository
public interface StandardRepository extends JpaRepository<Standard, UUID> {
	
	@Query("SELECT new com.anhtt.eTutor.dto.StandardInsertDTO(s.name, s.certificationImage , s.description) FROM Standard s LEFT OUTER JOIN Tutor t on t.id = s.tutor where t.id = :id")
	StandardInsertDTO getStandardByTutor(@Param("id") UUID id);

	List<Standard> findByisDeletedFalse();
}
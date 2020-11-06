package com.anhtt.eTutor.repository;

import com.anhtt.eTutor.dto.TutorShowDTO;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * TutorRepository
 */
@Repository
public interface TutorRepository extends JpaRepository<Tutor, UUID> {
    Tutor findByTutorAccount(Account account);
    
    @Query(value = "SELECT new com.anhtt.eTutor.dto.TutorShowDTO(t.fullname, t.avatar) FROM Tutor t LEFT OUTER JOIN Rating r on r.tutor = t.id where r.point >=5")
    List<TutorShowDTO> findByTutorByRating();
    
    List<Tutor> findByisDeleteFalse();
    
    
}
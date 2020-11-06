package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.*;

import java.util.List;
import java.util.UUID;

public interface TutorService {
    List<TutorShowDTO> getAll();
    TutorShowDTO getTutorInformation();
    List<MatchedTutorObject> searchTutorMatchingStudent(StudentRequirementObject studentRequirementObject);
    TutorShowDTO insert(TutorInsertDTO tutorInsertDTO) throws Exception;
    TutorShowDTO update(TutorUpdateDTO tutorUpdateDTO);
    void delete(UUID id);
    List<TutorShowDTO> findTutorByRating();
    TutorShowProfileDTO getTutorByID(UUID id);
}

package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.anhtt.eTutor.dto.HistoryRegistrationDTO;
import com.anhtt.eTutor.dto.RegistrationDTO;
import com.anhtt.eTutor.dto.RegistrationInDayObject;
import com.anhtt.eTutor.dto.RegistrationInsertDTO;


public interface RegistrationService {
	List<RegistrationDTO> retrieveAllRegistration();
	RegistrationDTO saveRegistration(RegistrationDTO reg);
	RegistrationDTO updateRegistration(RegistrationDTO reg);
	Optional<RegistrationDTO> findByIdRegis(Long id);
	void deleteRegistration(UUID id);

	RegistrationDTO registrationCourse(UUID courseId);
	List<HistoryRegistrationDTO> getHistory();

	List<RegistrationInDayObject> getCourseInDay();
}

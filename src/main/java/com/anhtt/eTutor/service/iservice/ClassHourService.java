package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.Optional;

import com.anhtt.eTutor.dto.ClassHourDTO;
import com.anhtt.eTutor.dto.SlotDTO;
import com.anhtt.eTutor.dto.StudentRegistrationObject;

public interface ClassHourService {

	List<ClassHourDTO> retrieveAllClassHour();
	ClassHourDTO saveClassHour(ClassHourDTO att);
	ClassHourDTO updateClassHour(ClassHourDTO att);
	Optional<ClassHourDTO> findByIdSlot(Long id);

	boolean createTimeTable(StudentRegistrationObject studentRegistrationObject) throws Exception;

}

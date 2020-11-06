package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.anhtt.eTutor.dto.AttendanceDTO;
import com.anhtt.eTutor.dto.AttendanceInsertDTO;
import com.anhtt.eTutor.dto.AttendanceObject;

public interface AttendanceService {
	List<AttendanceDTO> retrieveAllAttendance();
	boolean checkAttendance(AttendanceObject attendanceObject);
	AttendanceInsertDTO saveAttendance(AttendanceInsertDTO att);
	AttendanceDTO updateAttendance(AttendanceDTO att);
	Optional<AttendanceDTO> findByIdAtten(Long id);
	void deleteAtten(UUID id);
}

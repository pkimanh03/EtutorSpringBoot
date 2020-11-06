package com.anhtt.eTutor.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.anhtt.eTutor.dto.AttendanceObject;
import com.anhtt.eTutor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhtt.eTutor.dto.AttendanceDTO;
import com.anhtt.eTutor.dto.AttendanceInsertDTO;
import com.anhtt.eTutor.service.iservice.AttendanceService;
import com.anhtt.eTutor.service.serviceImpl.AttendanceServiceImpl;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/attendance")
public class AttendanceController {
	@Autowired
	AttendanceService service;

	@GetMapping
	public ResponseEntity<List<AttendanceDTO>> retrieveAllAttendance() {
		return ResponseEntity.ok(service.retrieveAllAttendance());
	}

	@PostMapping("/check-attendance")
	public ResponseEntity<String> checkAttendance(@RequestBody AttendanceObject attendanceObject) {
		boolean result = service.checkAttendance(attendanceObject);
		if (result)
			return ResponseEntity.ok(Constants.SUCCESS);
		return ResponseEntity.ok(Constants.FAILED);
	}

	@PostMapping
	public ResponseEntity<AttendanceInsertDTO> createAtten(@Valid @RequestBody AttendanceInsertDTO att) {

		return ResponseEntity.ok(service.saveAttendance(att));
	}

	@PutMapping
	public ResponseEntity<AttendanceDTO> updateAtten(@PathVariable Long id, @Valid @RequestBody AttendanceDTO att) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		att.setCreateAt(time);
		AttendanceDTO dto = service.updateAttendance(att);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable UUID id) {
		service.deleteAtten(id);
		return null;
	}

}

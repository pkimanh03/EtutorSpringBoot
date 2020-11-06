package com.anhtt.eTutor.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.anhtt.eTutor.dto.HistoryRegistrationDTO;
import com.anhtt.eTutor.dto.RegistrationInDayObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anhtt.eTutor.dto.RegistrationDTO;
import com.anhtt.eTutor.dto.RegistrationInsertDTO;
import com.anhtt.eTutor.service.iservice.RegistrationService;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/registration")
public class RegistrationController {
	@Autowired
	RegistrationService service;

	@GetMapping
	public ResponseEntity<List<RegistrationDTO>>  retrieveAllRegistration(){
		return ResponseEntity.ok(service.retrieveAllRegistration());
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	public ResponseEntity<?> createRegis(@Valid @RequestBody RegistrationDTO reg) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		reg.setCreateAt(time);
		RegistrationDTO result = service.saveRegistration(reg);
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/regis-course")
	public ResponseEntity<RegistrationDTO> registrationCourse(@RequestBody UUID courseId) {
		RegistrationDTO result = service.registrationCourse(courseId);
		if (result != null)	return ResponseEntity.ok(result);
		return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping
	public ResponseEntity<RegistrationDTO> updateRegis(@Valid @RequestBody RegistrationDTO reg){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		reg.setCreateAt(time);
		RegistrationDTO result = service.updateRegistration(reg);
        if(result != null)
            return ResponseEntity.ok(result);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRegis(@PathVariable UUID id) {
		service.deleteRegistration(id);
		return ResponseEntity.ok(null);
	}

	//this API is failed because dont have wrong data to test
	@GetMapping("/history")
	public ResponseEntity<List<HistoryRegistrationDTO>> getHistory() {
		List<HistoryRegistrationDTO> result = service.getHistory();
		return ResponseEntity.ok(result);
	}

	@GetMapping("/course-in-day")
	public ResponseEntity<List<RegistrationInDayObject>> getCourseInDay() {
		List<RegistrationInDayObject> result = service.getCourseInDay();
		return ResponseEntity.ok(result);
	}
}

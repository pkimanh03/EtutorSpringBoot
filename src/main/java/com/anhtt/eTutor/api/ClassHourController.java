package com.anhtt.eTutor.api;

import java.util.List;

import javax.validation.Valid;

import com.anhtt.eTutor.dto.StudentRegistrationObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhtt.eTutor.dto.ClassHourDTO;
import com.anhtt.eTutor.service.iservice.ClassHourService;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/classhour")
public class ClassHourController {

	@Autowired
	ClassHourService service;

	@GetMapping
	public ResponseEntity<List<ClassHourDTO>> retrieveAllClassHour(){
		return ResponseEntity.ok(service.retrieveAllClassHour());
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ClassHourDTO> createClass(@Valid @RequestBody ClassHourDTO hour) {
		return ResponseEntity.ok(service.saveClassHour(hour));
	}

	@PutMapping
	public ResponseEntity<ClassHourDTO> updateClass(@Valid @RequestBody ClassHourDTO hour){
		ClassHourDTO dto = service.updateClassHour(hour);
		if(dto!=null) {
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

	}

	@PostMapping(value = "/timetable", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> createTimeTable(@RequestBody StudentRegistrationObject studentRegistrationObject) {
		try {
			boolean result = service.createTimeTable(studentRegistrationObject);
			if (result) return ResponseEntity.ok(result);
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}

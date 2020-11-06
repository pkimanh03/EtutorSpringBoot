package com.anhtt.eTutor.api;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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


import com.anhtt.eTutor.dto.StandardDTO;

import com.anhtt.eTutor.service.iservice.StandarService;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/standard")
public class StandardController {
	
	@Autowired
	StandarService service;
	
	@GetMapping
	public ResponseEntity<List<StandardDTO>>  retrieveAllStandard(){
		return ResponseEntity.ok(service.getAll());
	}
	
	
	@PostMapping()
	public ResponseEntity<StandardDTO> createStand(@Valid @RequestBody StandardDTO stand) {
			
		return ResponseEntity.ok(service.saveStandar(stand));
	}
	
	@PutMapping
	public ResponseEntity<StandardDTO> updateStand( @Valid @RequestBody StandardDTO stand){	
		StandardDTO dto = service.updateStandar(stand);
		if(dto!=null) {
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity delete(@PathVariable UUID id) {
		service.deleteStandar(id);
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getStandardByTutor(@PathVariable UUID id) {
		return ResponseEntity.ok(service.getStandardByTutor(id));
	}

}

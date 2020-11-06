package com.anhtt.eTutor.api;

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

import com.anhtt.eTutor.dto.RatingDTO;

import com.anhtt.eTutor.service.iservice.RatingService;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/rating")
public class RatingController {
	@Autowired
	RatingService service;
	
	@GetMapping
	public ResponseEntity<List<RatingDTO>>  retrieveAllStandard(){
		return ResponseEntity.ok(service.getAll());
	}
	
	
	@PostMapping()
	public ResponseEntity<RatingDTO> createStand(@Valid @RequestBody RatingDTO rating) {
			
		return ResponseEntity.ok(service.insert(rating));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RatingDTO> updateStand( @Valid @RequestBody RatingDTO rating){	
		RatingDTO dto = service.update(rating);
		if(dto!=null) {
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity delete(@PathVariable UUID id) {
		service.delete(id);
		return null;
	}
}

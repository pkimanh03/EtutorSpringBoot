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


import com.anhtt.eTutor.dto.TransactionTypeDTO;
import com.anhtt.eTutor.service.iservice.TransactionTypeService;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/trans")
public class TransactionTypeController {
	
	@Autowired
	TransactionTypeService service;
	
	@GetMapping
	public ResponseEntity<List<TransactionTypeDTO>>  retrieveAllTrans(){
		return ResponseEntity.ok(service.getAll());
	}
	
	
	@PostMapping()
	public ResponseEntity<TransactionTypeDTO> createTrans(@Valid @RequestBody TransactionTypeDTO trans) {
			
		return ResponseEntity.ok(service.saveTrans(trans));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransactionTypeDTO> updateTrans(@PathVariable Long id, @Valid @RequestBody TransactionTypeDTO trans){	
		TransactionTypeDTO dto = service.updateTrans(trans);
		if(dto!=null) {
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity delete(@PathVariable UUID id) {
		service.deleteTrans(id);
		return null;
	}

}

package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.SlotDTO;
import com.anhtt.eTutor.dto.SlotInsertDTO;
import com.anhtt.eTutor.dto.SlotResponseObject;
import com.anhtt.eTutor.service.iservice.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/slot")
public class SlotController {

	@Autowired
	SlotService service;

	@GetMapping
	public ResponseEntity<List<SlotResponseObject>>  retrieveAllSlot(){
		return ResponseEntity.ok(service.retrieveAllSlot());
	}

	@PostMapping
	public ResponseEntity<SlotInsertDTO> createSlot(@Valid @RequestBody SlotInsertDTO slot) {
		return ResponseEntity.ok(service.saveSlot(slot));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SlotInsertDTO> updateSlot(@PathVariable UUID id, @Valid @RequestBody SlotInsertDTO slot){
		SlotInsertDTO dto = service.updateSlot(slot, id);
		if(dto!=null) {
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable UUID id) {
		service.deleteSlot(id);
		 return ResponseEntity.ok(null);
	}
}

package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.*;
import com.anhtt.eTutor.service.iservice.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public ResponseEntity<List<TutorShowDTO>> getAll() {
        List<TutorShowDTO> result = tutorService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/profile")
    public ResponseEntity<TutorShowDTO> getTutorInformation() {
        TutorShowDTO result = tutorService.getTutorInformation();
        if (result != null)
            return ResponseEntity.ok(result);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/matching")
    public ResponseEntity<List<MatchedTutorObject>> getTutorMatchingStudent(
            @RequestBody StudentRequirementObject studentRequirementObject) {

        List<MatchedTutorObject> result = tutorService.searchTutorMatchingStudent(studentRequirementObject);
        if (result.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TutorShowDTO> insert(@RequestBody TutorInsertDTO tutorDTO) {
        try {
            TutorShowDTO result = tutorService.insert(tutorDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<TutorShowDTO> update(@RequestBody TutorUpdateDTO tutorDTO) {
        TutorShowDTO result = tutorService.update(tutorDTO);
        if (result == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id) {
        tutorService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tutorrating")
    public ResponseEntity getTutorByRating() {
        return ResponseEntity.ok(tutorService.findTutorByRating());
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<TutorShowProfileDTO> getProfileTutorByID(@PathVariable UUID id) {
        TutorShowProfileDTO result = tutorService.getTutorByID(id);
        return ResponseEntity.ok(result);
    }
}

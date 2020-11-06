package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.DashboardResponseObject;
import com.anhtt.eTutor.dto.MajorsDTO;
import com.anhtt.eTutor.dto.MajorsInsertDTO;
import com.anhtt.eTutor.service.iservice.MajorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/majors")
public class MajorsController {

    @Autowired
    private MajorsService majorsService;

    @GetMapping
    public ResponseEntity<List<MajorsDTO>> getAll() {
        List<MajorsDTO> result = majorsService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MajorsDTO> insert(@RequestBody MajorsInsertDTO majorsInsertDTO) {
        MajorsDTO result = majorsService.insert(majorsInsertDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<MajorsDTO> update(@RequestBody MajorsDTO majorsUpdateDTO) {
        MajorsDTO result = majorsService.update(majorsUpdateDTO);
        if (result == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        majorsService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/getListMajors")
    public ResponseEntity<List<MajorsDTO>> getMajorMostRegis() {
        List<MajorsDTO> result = majorsService.getMajorsMostRegis();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getTop3Major")
    public ResponseEntity<List<MajorsDTO>> getTop3Major() {
        List<MajorsDTO> result = majorsService.getTop3MajorWithCourse();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<DashboardResponseObject>> getCourseForEachMajors() {
        List<DashboardResponseObject> result = majorsService.getCourseForEachMajors();
        return ResponseEntity.ok(result);
    }
}

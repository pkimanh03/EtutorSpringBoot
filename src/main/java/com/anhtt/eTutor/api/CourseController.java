package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.CourseDTO;
import com.anhtt.eTutor.dto.CourseInsertDTO;
import com.anhtt.eTutor.service.iservice.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> result = courseService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> insert(@RequestBody CourseInsertDTO courseDTO) {
        CourseDTO courseUpdateDTO = courseService.insert(courseDTO);
        return ResponseEntity.ok(courseUpdateDTO);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO courseUpdateDTO) {
        CourseDTO result = courseService.update(courseUpdateDTO);
        if (result == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        courseService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseDetail(@PathVariable UUID id) {
        CourseDTO result = courseService.getCourseDetail(id);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/getCourseByMajor/{id}")
    public ResponseEntity<List<CourseDTO>> getCourseByMajor(@PathVariable UUID id){
    	List<CourseDTO> result = courseService.getAllCourseByMajor(id);
    	return ResponseEntity.ok(result);
    }
    
    @GetMapping("/getTop20")
    public ResponseEntity<List<CourseDTO>> getTop20Course(){
    	List<CourseDTO> result = courseService.getTop20Course();
    	return ResponseEntity.ok(result);
    }


}

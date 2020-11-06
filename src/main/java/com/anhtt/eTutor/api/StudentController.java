package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.SigninStudentObject;
import com.anhtt.eTutor.dto.StudentInsertDTO;
import com.anhtt.eTutor.dto.StudentShowDTO;
import com.anhtt.eTutor.dto.StudentUpdateDTO;
import com.anhtt.eTutor.message.response.JwtResponse;
import com.anhtt.eTutor.model.Student;
import com.anhtt.eTutor.repository.StudentRepository;
import com.anhtt.eTutor.service.iservice.StudentService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping
    public ResponseEntity<List<StudentShowDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/profile")
    public ResponseEntity<StudentShowDTO> getStudentInformation() {
        StudentShowDTO result = studentService.getStudentInformation();
        if (result != null)
            return ResponseEntity.ok(result);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<StudentShowDTO> insert(@RequestBody StudentInsertDTO studentDTO)
            throws FirebaseAuthException, AddressException, MessagingException, IOException {

        return ResponseEntity.ok(studentService.insert(studentDTO));
    }

    @PutMapping
    public ResponseEntity<StudentShowDTO> update(@RequestBody StudentUpdateDTO studentDTO) {
        StudentShowDTO result = studentService.update(studentDTO);
        if (result != null)
            return ResponseEntity.ok(result);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(studentRepo.getStudentByID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) throws FirebaseAuthException {
        studentService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signin-gmail")
    public ResponseEntity<JwtResponse> signinGmail(@RequestBody SigninStudentObject studentAccount) {
        try {
            JwtResponse result = studentService.signinGmail(studentAccount);
            if (result == null)
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

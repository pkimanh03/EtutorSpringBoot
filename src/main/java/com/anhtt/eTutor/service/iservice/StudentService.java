package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.SigninStudentObject;
import com.anhtt.eTutor.dto.StudentInsertDTO;
import com.anhtt.eTutor.dto.StudentShowDTO;
import com.anhtt.eTutor.dto.StudentUpdateDTO;

import com.google.firebase.auth.FirebaseAuthException;

import com.anhtt.eTutor.message.response.JwtResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface StudentService {
    List<StudentShowDTO> getAll();
    StudentShowDTO getStudentInformation();
    StudentShowDTO insert(StudentInsertDTO studentDTO) throws FirebaseAuthException, AddressException, MessagingException, IOException;
    StudentShowDTO update(StudentUpdateDTO studentDTO);
    void delete(UUID id) throws FirebaseAuthException;
    void createFireBaseUser(StudentInsertDTO studentDTO);
    JwtResponse signinGmail(SigninStudentObject signinStudentObject) throws Exception;

}

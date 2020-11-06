package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.StudentInsertDTO;
import com.anhtt.eTutor.dto.StudentShowDTO;
import com.anhtt.eTutor.dto.StudentUpdateDTO;
import com.anhtt.eTutor.model.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentInsertDTO toStudentInsertDto(Student student);
    List<StudentInsertDTO> toStudentInsertDtoList(List<Student> studentList);

    StudentShowDTO toStudentShowDto(Student student);
    List<StudentShowDTO> toStudentShowDtoList(List<Student> studentList);

    StudentUpdateDTO toStudentUpdateDto(Student student);
    List<StudentUpdateDTO> toStudentUpdateDtoList(List<Student> studentList);

    Student toStudentInsert(StudentInsertDTO studentInsertDTO);
    Student toStudentUpdate(StudentUpdateDTO studentUpdateDTO);
    Student toStudentShow(StudentShowDTO studentShowDTO);
}

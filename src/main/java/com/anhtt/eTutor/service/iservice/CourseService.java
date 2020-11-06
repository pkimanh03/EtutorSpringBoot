package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.CourseDTO;
import com.anhtt.eTutor.dto.CourseInsertDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    List<CourseDTO> getAll();
    CourseDTO insert(CourseInsertDTO courseInsertDTO);
    CourseDTO update(CourseDTO courseUpdateDTO);
    void delete(UUID id);
    CourseDTO getCourseDetail(UUID id);
    List<CourseDTO> getAllCourseByMajor(UUID id);
    List<CourseDTO> getTop20Course();

}

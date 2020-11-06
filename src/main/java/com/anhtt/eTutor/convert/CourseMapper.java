package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.CourseDTO;
import com.anhtt.eTutor.dto.CourseInsertDTO;
import com.anhtt.eTutor.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toCourseDto(Course course);
    List<CourseDTO> toCourseDtoList(List<Course> courseList);

    CourseInsertDTO toCourseInsertDto(Course course);
    List<CourseInsertDTO> toCourseInsertDtoList(List<Course> courseList);

    Course toCourse(CourseDTO courseDTO);
    Course toCourseI(CourseInsertDTO courseInsertDTO);
}

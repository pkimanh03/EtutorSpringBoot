package com.anhtt.eTutor.service.serviceImpl;

import com.anhtt.eTutor.dto.DashboardItemObject;
import com.anhtt.eTutor.dto.DashboardResponseObject;
import com.anhtt.eTutor.dto.TutorShowDTO;
import com.anhtt.eTutor.model.Course;
import com.anhtt.eTutor.model.Majors;
import com.anhtt.eTutor.repository.CourseRepository;
import com.anhtt.eTutor.repository.MajorRepository;
import com.anhtt.eTutor.repository.TutorRepository;
import com.anhtt.eTutor.service.iservice.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final String TOP_3_MAJORS = "TRENDING MAJORSES";
    private final String TOP_3_COURSES = "TRENDING COURSES";
    private final String TOP_TUTORS = "SUGGESTED TUTORS";

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public List<DashboardResponseObject> getDataForDashboard() {
        List<DashboardResponseObject> result = new ArrayList<>();

        List<Majors> majorsList = majorRepository.findTop5Majors();
        List<Course> courseList = courseRepository.findTop5Courses();
        List<TutorShowDTO> tutorList = tutorRepository.findByTutorByRating();

        List<DashboardItemObject> majorsItemList = majorsList.stream().map(majors -> {
            DashboardItemObject item = new DashboardItemObject();
            item.setName(majors.getName());
            item.setDescription(majors.getDescription());
            item.setImage("null");
            return item;
        }).collect(Collectors.toList());

        List<DashboardItemObject> courseItemList = courseList.stream().map(course -> {
            DashboardItemObject item = new DashboardItemObject();
            item.setName(course.getName());
            item.setDescription(course.getDescription());
            item.setImage(course.getImage());
            return item;
        }).collect(Collectors.toList());

        List<DashboardItemObject> tutorItemList = tutorList.stream().map(tutorShowDTO -> {
            DashboardItemObject item = new DashboardItemObject();
            item.setName(tutorShowDTO.getFullname());
            item.setDescription(tutorShowDTO.getEmail());
            item.setImage(tutorShowDTO.getAvatar());
            return item;
        }).collect(Collectors.toList());

        DashboardResponseObject r1 = new DashboardResponseObject(TOP_3_MAJORS, majorsItemList);
        DashboardResponseObject r2 = new DashboardResponseObject(TOP_3_COURSES, courseItemList);
        DashboardResponseObject r3 = new DashboardResponseObject(TOP_TUTORS, tutorItemList);

        result.add(r1);
        result.add(r2);
        result.add(r3);
        return result;
    }
}

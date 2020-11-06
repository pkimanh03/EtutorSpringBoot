package com.anhtt.eTutor.service.serviceImpl;

import com.anhtt.eTutor.convert.CourseMapper;
import com.anhtt.eTutor.dto.CourseDTO;
import com.anhtt.eTutor.dto.CourseInsertDTO;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Course;
import com.anhtt.eTutor.model.Majors;
import com.anhtt.eTutor.model.Tutor;
import com.anhtt.eTutor.repository.*;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private MajorRepository majorRepository;

    private String getEmailByToken() {
        String[] header = request.getHeader("Authorization").split(" ");
        String token = header[header.length - 1];
        String email = jwtProvider.getEmailFromJwtToken(token);
        return email;
    }

    @Override
    public List<CourseDTO> getAll() {
        List<Course> courseList = courseRepository.findByisDeletedFalse();
        List<CourseDTO> result = courseMapper.toCourseDtoList(courseList);
        return result;
    }

    @Override
    public CourseDTO insert(CourseInsertDTO courseInsertDTO) {
        Course course = courseMapper.toCourseI(courseInsertDTO);

        String email = getEmailByToken();
        Account account = accountRepository.findAccountByEmail(email);
        Tutor tutor = tutorRepository.findByTutorAccount(account);
        course.setTutor(tutor);

        Majors majors = majorRepository.findById(courseInsertDTO.getMajorsId()).get();
        course.setMajors(majors);

        course = courseRepository.save(course);
        CourseDTO result = courseMapper.toCourseDto(course);
        return result;
    }

    @Override
    public CourseDTO update(CourseDTO courseUpdateDTO) {
        Course course = courseRepository.findById(courseUpdateDTO.getId()).get();
        if(course == null) {
            return null;
        } else {
            course.setDescription(courseUpdateDTO.getDescription());
            course.setImage(courseUpdateDTO.getImage());
            course.setName(courseUpdateDTO.getName());
            course = courseRepository.save(course);
            CourseDTO result = courseMapper.toCourseDto(course);
            return result;
        }
    }

    @Override
    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }

	@Override
	public CourseDTO getCourseDetail(UUID id) {
		Course result = courseRepository.findById(id).get();
		
		return courseMapper.toCourseDto(result);
	}
	
	@Override
	public List<CourseDTO> getAllCourseByMajor(UUID id) {
		List<Course> listCourse = courseRepository.findByMajorID(id);
		List<CourseDTO> result = courseMapper.toCourseDtoList(listCourse);
		return result;
	}

	@Override
	public List<CourseDTO> getTop20Course() {
		List<Course> listCourse = courseRepository.findTop20Course();
		List<CourseDTO> result = courseMapper.toCourseDtoList(listCourse);
		return result;
	}

    
    
}

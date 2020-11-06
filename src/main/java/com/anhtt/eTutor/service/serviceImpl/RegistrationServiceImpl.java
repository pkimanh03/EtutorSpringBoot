package com.anhtt.eTutor.service.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Converter;
import javax.servlet.http.HttpServletRequest;

import com.anhtt.eTutor.dto.*;
import com.anhtt.eTutor.model.*;
import com.anhtt.eTutor.repository.*;
import com.anhtt.eTutor.utils.Constants;
import com.anhtt.eTutor.utils.DistinctUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.RegistrationMapper;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.RegistrationService;

import static com.anhtt.eTutor.utils.DistinctUtils.distinctByKey;

@Service

public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepository regis;

	@Autowired
	CourseRepository course;

	@Autowired
	StudentRepository stuRepo;

	@Autowired
	ClassHourRepository classHourRepository;

	@Autowired
	private RegistrationMapper regisMapper;
	
	@Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private AccountRepository accountRepo;
    
    
    private String getEmailByToken() {
        String[] header = request.getHeader("Authorization").split(" ");
        String token = header[header.length - 1];
        String email = jwtProvider.getEmailFromJwtToken(token);
        return email;
    }

	@Override
	public List<RegistrationDTO> retrieveAllRegistration() {
		List<Registration> regisList = regis.findByisDeletedFalse();
        return regisMapper.toRegistrationDtoList(regisList);
	}

	@Override
	public RegistrationDTO saveRegistration(RegistrationDTO att) {
		
		Registration registra = regisMapper.toRegistration(att);
		Course curCourse = course.findById(att.getCourseId()).get();
		if (curCourse == null) {
			return null;
		}
		String email = getEmailByToken();
		Account account = accountRepo.findAccountByEmail(email);
		Student curStu = stuRepo.findByStudentAccount(account);
			
		registra.setStudent(curStu);
		registra.setCourse(curCourse);
		registra = regis.save(registra);
		return regisMapper.toRegistrationDto(registra);
	}

	@Override
	public RegistrationDTO updateRegistration(RegistrationDTO att) {
		Optional<Registration> registration = regis.findById(att.getId());
		if(registration != null) {
			Optional<Course> curCourse = course.findById(att.getCourseId());
			String email = getEmailByToken();
			Account account = accountRepo.findAccountByEmail(email);
			
			Student curStu = stuRepo.findByStudentAccount(account);
			
			Registration entity = regisMapper.toRegistration(att);
			entity.setStudent(curStu);
			entity.setCourse(curCourse.get());
			regis.save(entity);
			return att;
		}
		return null;
	}

	@Override
	public Optional<RegistrationDTO> findByIdRegis(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRegistration(UUID id) {
		regis.deleteById(id);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public RegistrationDTO registrationCourse(UUID courseId) {
		RegistrationDTO result = null;
    	Course courseObj = course.findCourseById(courseId);
		Account account = accountRepo.findAccountByEmail(getEmailByToken());
		Student student = stuRepo.findByStudentAccount(account);
		Timestamp current = new Timestamp(System.currentTimeMillis());

		Registration registration = new Registration();
		registration.setCourse(courseObj);
		registration.setStudent(student);
		registration.setCreateAt(current);
		registration.setStatus(Constants.REGISTRATION_CREATED);

		registration = regis.save(registration);
		result = regisMapper.toRegistrationDto(registration);
		return result;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public List<HistoryRegistrationDTO> getHistory() {
		List<HistoryRegistrationDTO> result = new ArrayList<>();
    	String email = getEmailByToken();
		Account studentAccount = accountRepo.findAccountByEmail(email);
		Student student = stuRepo.findByStudentAccount(studentAccount);

		List<Registration> registrationList = regis.findRegistrationsByStudent(student);
		for (Registration registration:registrationList) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			HistoryRegistrationDTO historyRegistrationDTO = new HistoryRegistrationDTO();
			historyRegistrationDTO.setRegistrationId(registration.getId());
			historyRegistrationDTO.setCourseName(registration.getCourse().getName());
			historyRegistrationDTO.setCoursePrice(registration.getCourse().getPrice());
			historyRegistrationDTO.setCreateAt(registration.getCreateAt());
			historyRegistrationDTO.setStatus(registration.getStatus());
			historyRegistrationDTO.setCreateTimeString(simpleDateFormat.format(historyRegistrationDTO.getCreateAt()));
			historyRegistrationDTO.setCourseId(registration.getCourse().getId());
			historyRegistrationDTO.setTutorId(registration.getCourse().getTutor().getId());
			historyRegistrationDTO.setTutorName(registration.getCourse().getTutor().getFullname());
			List<ClassHour> classHourList = classHourRepository.findClassHoursByRegistration(registration);
			List<TimeTableObject> timeTableObjectList = new ArrayList<>();
			for (ClassHour classHour:classHourList) {
				TimeTableObject timeTableObject = new TimeTableObject();
				timeTableObject.setId(classHour.getId());
				timeTableObject.setSlotName(classHour.getSlot().getName());
				timeTableObject.setDate(classHour.getClasshourDate());
				if (timeTableObject.getDate() != null) {
					timeTableObject.setDateString(simpleDateFormat.format(timeTableObject.getDate()));
				}
				if (classHour.getAttendance() != null) {
					timeTableObject.setAttendanceStatus(classHour.getAttendance().getStatus());
				}

				timeTableObjectList.add(timeTableObject);
			}
			historyRegistrationDTO.setTimeTableObjectList(timeTableObjectList);
			result.add(historyRegistrationDTO);
		}

		result = result.stream().sorted(Comparator.comparing(HistoryRegistrationDTO::getCreateAt).reversed()).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<RegistrationInDayObject> getCourseInDay() {
		List<Registration> registrationList = regis.findAll();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<RegistrationInDayObject> result = registrationList.stream().map(registration -> {
			RegistrationInDayObject registrationInDayObject = new RegistrationInDayObject();
			registrationInDayObject.setDate(formatter.format(registration.getCreateAt()));
			return registrationInDayObject;
		}).collect(Collectors.toList());

		result = result.stream()
				.filter(distinctByKey(p -> p.getDate()))
				.collect(Collectors.toList());
		for (RegistrationInDayObject object:result) {
			List<Registration> registrationInDay = regis.findByCreatedAt("%" + object.getDate() + "%");
			object.setCountCourse(registrationInDay.size());
			List<String> courseNames = registrationInDay.stream().map(registration -> {
				return registration.getCourse().getName();
			}).collect(Collectors.toList());
			object.setCourseNameList(courseNames);
		}
		return  result;
	}

}

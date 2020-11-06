package com.anhtt.eTutor.service.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.anhtt.eTutor.dto.ClassHourInsertDTO;
import com.anhtt.eTutor.dto.StudentRegistrationObject;
import com.anhtt.eTutor.model.*;
import com.anhtt.eTutor.repository.*;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.utils.Constants;
import com.anhtt.eTutor.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.ClassHourMapper;
import com.anhtt.eTutor.dto.ClassHourDTO;
import com.anhtt.eTutor.service.iservice.ClassHourService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class ClassHourServiceImpl implements ClassHourService{

	@Autowired
	ClassHourRepository classHourRepository;

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	AttendanceRepository attendanceRepository;
	@Autowired
	SlotRepository slotRepository;

	@Autowired
	ClassHourMapper hourMapper;
	@Autowired
	EmailSender emailSender;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private JwtProvider jwtProvider;

//    private

	private String getEmailByToken() {
		String[] header = request.getHeader("Authorization").split(" ");
		String token = header[header.length - 1];
		String email = jwtProvider.getEmailFromJwtToken(token);
		return email;
	}
	@Override
	public List<ClassHourDTO> retrieveAllClassHour() {
		List<ClassHour> hourList = classHourRepository.findByisDeletedFalse();
        return hourMapper.toClassHourDtoList(hourList);
	}

	@Override
	public ClassHourDTO saveClassHour(ClassHourDTO classHourr) {
		ClassHour hour = hourMapper.toClassHour(classHourr);
		Optional<Registration> curReg = registrationRepository.findById(classHourr.getRegistrationId());
		if(curReg == null) {
			return null;

		}
		Optional<Slot> curSlot = slotRepository.findById(classHourr.getSlotId());
		if(curSlot == null)
			return null;
		hour.setSlot(curSlot.get());
		hour.setRegistration(curReg.get());
		classHourRepository.save(hour);
		return hourMapper.toClassHourDto(hour);
	}

	@Override
	public ClassHourDTO updateClassHour(ClassHourDTO classHourr) {
		Optional<ClassHour> hour = classHourRepository.findById(classHourr.getId());
		if(hour!=null) {
			Optional<Registration> curReg = registrationRepository.findById(classHourr.getRegistrationId());
			Optional<Slot> curSlot = slotRepository.findById(classHourr.getSlotId());
			
			ClassHour cl = hourMapper.toClassHour(classHourr);
			cl.setSlot(curSlot.get());
			cl.setRegistration(curReg.get());
			classHourRepository.save(cl);
			return classHourr;

		}

		return null;
	}

	@Override
	public Optional<ClassHourDTO> findByIdSlot(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean createTimeTable(StudentRegistrationObject studentRegistrationObject) throws Exception {
		List<ClassHourInsertDTO> insertDTOList = studentRegistrationObject.getClassHourInsertDTOList();
		Registration registration = registrationRepository.findById(studentRegistrationObject.getRegistrationId()).get();

		Course course = registration.getCourse();
		Tutor tutor = course.getTutor();
		Account tutorAccount = tutor.getTutorAccount();
		Student student = studentRepository.findStudentByStudentAccount_Email(getEmailByToken());


		List<ClassHour> classHourList = new ArrayList<>();
		for (ClassHourInsertDTO classHourInsertDTO:insertDTOList) {
			Slot slotObj = slotRepository.findById(classHourInsertDTO.getSlotId()).get();

			ClassHour classhour = new ClassHour();
			classhour.setRegistration(registration);

			classhour.setSlot(slotObj);
//			classhour.setClasshourDate(new Timestamp(System.currentTimeMillis()));
			classhour.setClasshourDate(classHourInsertDTO.getClasshourDate());
			classhour.setStatus(Constants.CLASSHOUR_CREATED);
			classhour = classHourRepository.save(classhour);

			Attendance attendance = new Attendance();
			attendance.setClassHour(classhour);
			attendance.setTutor(tutor);
			attendance.setStudent(student);
			attendance.setStatus(Constants.ATTENDANCE_NOTYET);
			attendance.setCreateAt(new Timestamp(System.currentTimeMillis()));
			attendance = attendanceRepository.save(attendance);

			classHourList.add(classhour);
		}

		if (!classHourList.isEmpty()) {
			String[] timetableArray = new String[classHourList.size()];
			for (int i = 0; i < classHourList.size(); i++) {
				String date = classHourList.get(i).getClasshourDate().toString();
				String slot = classHourList.get(i).getSlot().getName();
				String timetable = slot + ":" + date;
				timetableArray[i] = timetable;
			}
			String tutorEmail = tutorAccount.getEmail();
			emailSender.sendEmailSuccessTransactionStu(getEmailByToken(), course.getName(), timetableArray, tutorEmail);
//			emailSender.sendEmail(getEmailByToken());
//			emailSender.sendEmail(tutorEmail);
			return true;
		}
		return false;
	}


}

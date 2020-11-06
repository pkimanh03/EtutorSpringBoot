package com.anhtt.eTutor.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.anhtt.eTutor.dto.AttendanceObject;
import com.anhtt.eTutor.model.*;
import com.anhtt.eTutor.repository.*;
import com.anhtt.eTutor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.AttendanceMapper;
import com.anhtt.eTutor.dto.AttendanceDTO;
import com.anhtt.eTutor.dto.AttendanceInsertDTO;
import com.anhtt.eTutor.service.iservice.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository atten;

	@Autowired
	StudentRepository stuRepo;

	@Autowired
	TutorRepository tuRepo;

	@Autowired
	AttendanceMapper attenMapper;

	@Autowired
	ClassHourRepository classHour;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<AttendanceDTO> retrieveAllAttendance() {
		List<Attendance> attenList = atten.findByisDeleteFalse();
        return attenMapper.toAttendanceDtoList(attenList);
	}

	@Override
	public boolean checkAttendance(AttendanceObject attendanceObject) {
		boolean result = false;

		Role tutor = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
		Role student = roleRepository.findByName(RoleName.ROLE_STUDENT).get();
		Account account = accountRepository.findAccountByEmail(attendanceObject.getEmail());
		Attendance attendance = atten.findByClassHour_Id(attendanceObject.getClasshourId());
		if (account != null && attendance != null) {
			String attendanceStatus = attendance.getStatus();
			Set<Role> accountRole = account.getRoles();
			if (accountRole.contains(tutor) && attendanceStatus.equals(Constants.ATTENDANCE_NOTYET)) {
				attendance.setStatus(Constants.ATTENDANCE_TUTOR);
			}
			if(accountRole.contains(student) && attendanceStatus.equals(Constants.ATTENDANCE_NOTYET)) {
				attendance.setStatus(Constants.ATTENDANCE_STUDENT);
			}
			if(accountRole.contains(tutor) && attendanceStatus.equals(Constants.ATTENDANCE_STUDENT)) {
				attendance.setStatus(Constants.ATTENDANCE_PRESENT);
			}
			if(accountRole.contains(student) && attendance.equals(Constants.ATTENDANCE_TUTOR)) {
				attendance.setStatus(Constants.ATTENDANCE_PRESENT);
			}
			atten.save(attendance);
			result = true;
		}

		return result;
	}

	@Override
	public AttendanceInsertDTO saveAttendance(AttendanceInsertDTO att) {
		Attendance attendance = attenMapper.toAttendanceI(att);

		Optional<Student> curStu = stuRepo.findById(att.getStudentId());

		if (curStu == null) {
			return null;
		}

		Optional<Tutor> curTu = tuRepo.findById(att.getTutorId());
		if(curTu == null) {
			return null;
		}
		Optional<ClassHour> curHour = classHour.findById(att.getClassHourId());
		attendance.setClassHour(curHour.get());
		attendance.setStudent(curStu.get());
		attendance.setTutor(curTu.get());
		atten.save(attendance);
		return attenMapper.toAttendanceInsertDto(attendance);
	}

	@Override
	public AttendanceDTO updateAttendance(AttendanceDTO att) {
		Optional<Attendance> attendance = atten.findById(att.getId());
		if(attendance!=null) {
			Optional<Tutor> curTu = tuRepo.findById(att.getTutorId());
			Optional<Student> curStu = stuRepo.findById(att.getStudentId());
			
			Attendance a = attenMapper.toAttendance(att);
			Optional<ClassHour> curHour = classHour.findById(att.getClassHourId());
			a.setClassHour(curHour.get());
			a.setTutor(curTu.get());
			a.setStudent(curStu.get());
			atten.save(a);
			return att;

		}

		return null;
	}

	@Override
	public Optional<AttendanceDTO> findByIdAtten(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAtten(UUID id) {
		atten.deleteById(id);
	}


}

package com.anhtt.eTutor.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.anhtt.eTutor.convert.CourseMapper;
import com.anhtt.eTutor.convert.TutorMapper;
import com.anhtt.eTutor.dto.*;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.AvailableTime;
import com.anhtt.eTutor.model.Course;
import com.anhtt.eTutor.model.Role;
import com.anhtt.eTutor.model.RoleName;
import com.anhtt.eTutor.model.Tutor;
import com.anhtt.eTutor.repository.AccountRepository;
import com.anhtt.eTutor.repository.AvailableTimeRepository;
import com.anhtt.eTutor.repository.CourseRepository;
import com.anhtt.eTutor.repository.RoleRepository;
import com.anhtt.eTutor.repository.TutorRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.TutorService;
import com.anhtt.eTutor.utils.Constants;
import com.anhtt.eTutor.utils.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import com.anhtt.eTutor.mybatis.TutorMyBatis;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AvailableTimeRepository availableTimeRepository;
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RestTemplate restTemplate;

    private String getEmailByToken() {
        String[] header = request.getHeader("Authorization").split(" ");
        String token = header[header.length - 1];
        String email = jwtProvider.getEmailFromJwtToken(token);
        return email;
    }

    @Override
    public List<TutorShowDTO> getAll() {
        List<Tutor> tutorList = tutorRepository.findByisDeleteFalse();
        tutorList.stream().filter(tutor -> {
            return tutor.isDelete() == true;
        }).collect(Collectors.toList());
//        for (Tutor t: tutorList) {
//            if(t.isDelete())
//                tutorList.remove(t);
//        }
        List<TutorShowDTO> result = tutorMapper.toTutorShowDtoList(tutorList);
        return result;
    }

    @Override
    public TutorShowDTO getTutorInformation() {
        TutorShowDTO result = null;
        String email = getEmailByToken();
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null) {
            Tutor tutor = tutorRepository.findByTutorAccount(account);
            result = tutorMapper.toTutorShowDto(tutor);
            result.setEmail(email);
        }
        return result;
    }

    @Override
    public List<MatchedTutorObject> searchTutorMatchingStudent(StudentRequirementObject studentRequirementObject) {
        List<MatchedTutorObject> result = new ArrayList<>();
        List<Course> courseList = courseRepository.findCoursesByNameLike(studentRequirementObject.getCourseName());
        List<UUID> tutorIdList = courseList.stream().map(course -> {
            return course.getTutor().getId();
        }).collect(Collectors.toList());
        tutorIdList = tutorIdList.stream().distinct().collect(Collectors.toList());

        for (UUID id : tutorIdList) {
            Tutor _tutor = tutorRepository.getOne(id);
            List<String> tutorAvailableSlot = new ArrayList<>();
            AvailableTime availableTime = availableTimeRepository.findByTutor_Id(id);
            String[] arr = availableTime.getSlotInWeek().split(",");
            for (int i = 0; i < arr.length; i++) {
                tutorAvailableSlot.add(arr[i]);
            }
            int tutorPoint = 0;
            for (String slotId : studentRequirementObject.getSlotList()) {
                if (tutorAvailableSlot.contains(slotId)) {
                    tutorPoint++;
                }
            }
            if (tutorPoint / studentRequirementObject.getSlotList().size() > 0.8) {
                MatchedTutorObject matchedTutorObject = new MatchedTutorObject();
                matchedTutorObject.setTutorId(id);
                matchedTutorObject.setAvatar(_tutor.getAvatar());
                matchedTutorObject.setFullname(_tutor.getFullname());
                List<Course> tutorCourseList = courseRepository.findCoursesByTutor_Id(id);
                List<CourseDTO> courseDtoList = courseMapper.toCourseDtoList(tutorCourseList);
                matchedTutorObject.setCourseList(courseDtoList);
                result.add(matchedTutorObject);
            }
        }

//         tutorAvailableSlot.entrySet().forEach(uuidEntry -> {
//             int count = 0;
//             int total = uuidEntry.getValue().length;
//             for (String slotId:uuidEntry.getValue()) {
//                 if (studentRequirementObject.getSlotList().contains(slotId)) {
//                     count++;
//                     if ((count/total)*100 >= 80) {
//                         MatchedTutorObject matchedTutorObject = new MatchedTutorObject();

//                         Tutor tutor = tutorRepository.findById(uuidEntry.getKey()).get();
//                         matchedTutorObject.setTutorId(tutor.getId());
//                         matchedTutorObject.setAvatar(tutor.getAvatar());
//                         matchedTutorObject.setFullname(tutor.getFullname());

//                         List<Course> tutorCourseList = courseRepository.findCoursesByTutor_Id(uuidEntry.getKey());
//                         List<CourseInsertDTO> courseInsertDTOList = courseMapper.toCourseInsertDtoList(tutorCourseList);
//                         matchedTutorObject.setCourseList(courseInsertDTOList);
// //                        matchedTutorObject.setCoursePrice(course.getPrice());
// //                        matchedTutorObject.setCourseDescription(course.getDescription());

//                         result.add(matchedTutorObject);
//                         break;
//                     }
//                 }
//             }
//         });
        return result;
    }

    @Override
    public TutorShowDTO insert(TutorInsertDTO tutorInsertDTO) throws Exception {
        String imageName = null;
        ImageObject imageObject = null;
        HttpEntity<ImageObject> requestBobyObject = null;
        String resultURL = null;

        Account account = new Account(tutorInsertDTO.getEmail(), encoder.encode(tutorInsertDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(RoleName.ROLE_TUTOR).get();
        roles.add(role);
        account.setRoles(roles);
        account.setBalance(new Long(0));
        account = accountRepository.save(account);

        imageName = System.currentTimeMillis() + "";
        imageObject = new ImageObject(tutorInsertDTO.getAvatar(), imageName);
        requestBobyObject = new HttpEntity<>(imageObject);
        resultURL = restTemplate.exchange(Constants.API_IMAGE_URI, HttpMethod.POST, requestBobyObject, String.class).getBody();
        tutorInsertDTO.setAvatar(resultURL);

        imageName = System.currentTimeMillis() + "";
        imageObject = new ImageObject(tutorInsertDTO.getIdCardImg(), imageName);
        requestBobyObject = new HttpEntity<>(imageObject);
        resultURL = restTemplate.exchange(Constants.API_IMAGE_URI, HttpMethod.POST, requestBobyObject, String.class).getBody();
        tutorInsertDTO.setIdCardImg(resultURL);

        imageName = System.currentTimeMillis() + "";
        imageObject = new ImageObject(tutorInsertDTO.getDrivingLicenseImg(), imageName);
        requestBobyObject = new HttpEntity<>(imageObject);
        resultURL = restTemplate.exchange(Constants.API_IMAGE_URI, HttpMethod.POST, requestBobyObject, String.class).getBody();
        tutorInsertDTO.setDrivingLicenseImg(resultURL);


        Tutor tutor = tutorMapper.toTutorInsert(tutorInsertDTO);
        
        tutor.setTutorAccount(account);
        tutor = tutorRepository.save(tutor);

        if (tutor != null) {
            emailSender.sendEmail(account.getEmail());
        }

        TutorShowDTO result = tutorMapper.toTutorShowDto(tutor);
        return result;
    }

    @Override
    public TutorShowDTO update(TutorUpdateDTO tutorUpdateDTO) {
        String email = getEmailByToken();
        Account account = accountRepository.findAccountByEmail(email);
        Tutor tutor = tutorMapper.toTutorUpdate(tutorUpdateDTO);
        tutor.setTutorAccount(account);

        UUID tutorId = tutorRepository.findByTutorAccount(account).getId();
        tutor.setId(tutorId);

        tutor = tutorRepository.save(tutor);
        TutorShowDTO result = tutorMapper.toTutorShowDto(tutor);
        return result;
    }

    @Override
    public void delete(UUID id) {
        Tutor tutor = tutorRepository.findById(id).get();
        if (tutor != null) {
            tutor.setDelete(true);

            Account account = tutor.getTutorAccount();
            account.setDelete(true);

            accountRepository.save(account);
            tutorRepository.save(tutor);
        }
    }

	@Override
	public List<TutorShowDTO> findTutorByRating() {
		
		List<TutorShowDTO> result = tutorRepository.findByTutorByRating();
		return result;
	}

	@Override
	public TutorShowProfileDTO getTutorByID(UUID id) {
		Tutor tutor = tutorRepository.findById(id).get();
		String email = getEmailByToken();
		
		TutorShowProfileDTO result = tutorMapper.toTutorShowProfile(tutor);
		
		return result;
	}
	
	
}

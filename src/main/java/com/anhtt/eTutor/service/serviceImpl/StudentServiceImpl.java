package com.anhtt.eTutor.service.serviceImpl;

import com.anhtt.eTutor.AdminSDKGenerator;
import com.anhtt.eTutor.convert.StudentMapper;
import com.anhtt.eTutor.dto.SigninStudentObject;
import com.anhtt.eTutor.dto.StudentInsertDTO;
import com.anhtt.eTutor.dto.StudentShowDTO;
import com.anhtt.eTutor.dto.StudentUpdateDTO;
import com.anhtt.eTutor.message.response.JwtResponse;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Role;
import com.anhtt.eTutor.model.RoleName;
import com.anhtt.eTutor.model.Student;
import com.anhtt.eTutor.repository.AccountRepository;
import com.anhtt.eTutor.repository.RoleRepository;
import com.anhtt.eTutor.repository.StudentRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.StudentService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.anhtt.eTutor.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EmailSender emailSender;

	private String getEmailByToken() {
		String[] header = request.getHeader("Authorization").split(" ");
		String token = header[header.length - 1];
		String email = jwtProvider.getEmailFromJwtToken(token);
		return email;
	}

	@Override
	public List<StudentShowDTO> getAll() {
		List<Student> studentList = studentRepository.findByisDeleteFalse();
		List<StudentShowDTO> result = studentMapper.toStudentShowDtoList(studentList);
		return result;
	}

	@Override
	public StudentShowDTO getStudentInformation() {
		StudentShowDTO result = null;
		String email = getEmailByToken();
		Account account = accountRepository.findAccountByEmail(email);
		if (account != null) {
			Student student = studentRepository.findByStudentAccount(account);
			result = studentMapper.toStudentShowDto(student);
			result.setEmail(email);
		}
		return result;
	}

	@Override
	public StudentShowDTO insert(StudentInsertDTO studentInsertDTO)
			throws FirebaseAuthException, AddressException, MessagingException, IOException {
		try {
			FirebaseApp.getInstance();
		} catch (IllegalStateException ex) {
			try {
				FileInputStream serviceAccount =
						new FileInputStream("etutor-faf5c-firebase-adminsdk-lwbci-5fa22bf04d.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.setDatabaseUrl("https://etutor-faf5c.firebaseio.com")
						.build();

				FirebaseApp.initializeApp(options);
				System.out.println("Firebase has been init");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		Account oldAccount = accountRepository.findAccountByEmail(studentInsertDTO.getEmail());
		Student oldStudent = studentRepository.findByStudentAccount(oldAccount);
		Student student = studentMapper.toStudentInsert(studentInsertDTO);
		if (oldAccount == null && oldStudent ==null) {
			createFireBaseUser(studentInsertDTO);
			String uid = FirebaseAuth.getInstance().getUserByEmail(studentInsertDTO.getEmail()).getUid();
			Account account = new Account(studentInsertDTO.getEmail(), encoder.encode(studentInsertDTO.getPassword()),
					uid);
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(RoleName.ROLE_STUDENT).get();
			roles.add(role);
			account.setRoles(roles);
			account.setBalance(new Long(0));

			student.setStudentAccount(account);
			student = studentRepository.save(student);
			emailSender.sendEmail(account.getEmail());

		} else {
			createFireBaseUser(studentInsertDTO);
			String uid = FirebaseAuth.getInstance().getUserByEmail(studentInsertDTO.getEmail()).getUid();
			oldAccount.setTokenGmail(uid);
			oldAccount.setDelete(false);
			accountRepository.save(oldAccount);
			oldStudent.setDelete(false);
			oldStudent.setStudentAccount(oldAccount);
			
			studentRepository.save(oldStudent);
			
			
		}
		StudentShowDTO result = studentMapper.toStudentShowDto(student);
		return result;
	}

	@Override
	public StudentShowDTO update(StudentUpdateDTO studentUpdateDTO) {
		String email = getEmailByToken();
		Account account = accountRepository.findAccountByEmail(email);

		Student student = studentMapper.toStudentUpdate(studentUpdateDTO);
		student.setStudentAccount(account);

		UUID studentId = studentRepository.findByStudentAccount(account).getId();
		student.setId(studentId);

		student = studentRepository.save(student);
		StudentShowDTO result = studentMapper.toStudentShowDto(student);
		return result;
	}

	@Override
	public void delete(UUID id) throws FirebaseAuthException {
		Student student = studentRepository.findById(id).get();
		Account acc = accountRepository.findById(student.getStudentAccount().getId()).get();
		System.out.println(acc);
		String uid = FirebaseAuth.getInstance().getUserByEmail(acc.getEmail()).getUid();
		if (student != null) {
			student.setDelete(true);

			Account account = student.getStudentAccount();
			account.setDelete(true);

			FirebaseAuth.getInstance().deleteUser(uid);
			accountRepository.save(account);
			studentRepository.save(student);
		}
	}

	public void createFireBaseUser(StudentInsertDTO stu) {
		try {
			CreateRequest cr = new CreateRequest();
			cr.setPassword(stu.getPassword()).setEmail(stu.getEmail()).setEmailVerified(false).setDisabled(false);
			FirebaseAuth.getInstance().createUser(cr);

		} catch (FirebaseAuthException e) {
			System.out.println(e.getErrorCode());
		}
	}

	@Override
	@Transactional
	public JwtResponse signinGmail(SigninStudentObject signinStudentObject) throws Exception {
		JwtResponse result = null;
		String email = signinStudentObject.getEmail();
		String uid = signinStudentObject.getUid();
		Account account = accountRepository.findAccountByEmail(email);
		if (account != null) {
			String token = account.getTokenGmail();
			if (token == null) {
				account.setTokenGmail(uid);
				accountRepository.save(account);
				String jwt = jwtProvider.generateTokenForSiginGmail(account.getEmail());
				result = new JwtResponse(jwt);
			} else {
				if (token.equals(signinStudentObject.getUid())) {
					;
					String jwt = jwtProvider.generateTokenForSiginGmail(account.getEmail());
					result = new JwtResponse(jwt);
				}
			}
		} else {
			Account newAccount = new Account();
			newAccount.setEmail(email);
			newAccount.setBalance(new Long(0));
			newAccount.setTokenGmail(uid);
			Role studentRole = roleRepository.findByName(RoleName.ROLE_STUDENT).get();
			Set<Role> roles = new HashSet<>();
			roles.add(studentRole);
			newAccount.setRoles(roles);
			newAccount = accountRepository.save(newAccount);

			Student student = new Student();
			student.setFullname(signinStudentObject.getFullname());
			student.setStudentAccount(newAccount);
			student = studentRepository.save(student);

			if (newAccount != null && student != null) {
				emailSender.sendEmail(newAccount.getEmail());
				String jwt = jwtProvider.generateTokenForSiginGmail(newAccount.getEmail());
				result = new JwtResponse(jwt);
			}
		}
		return result;

	}
}

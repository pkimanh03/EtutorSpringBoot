package com.anhtt.eTutor;

import com.anhtt.eTutor.repository.AccountRepository;
import com.anhtt.eTutor.repository.RoleRepository;
import com.anhtt.eTutor.repository.SlotRepository;
import com.anhtt.eTutor.repository.TransactionTypeRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.anhtt.eTutor.model.*;
import java.util.*;
import com.anhtt.eTutor.utils.Constants;

@SpringBootApplication
@EnableScheduling
public class SpringBootJwtAuthenticationApplication extends SpringBootServletInitializer implements ApplicationRunner {

	@Value("${app.firebase-configuration-file}")
    private static String firebaseConfigPath;
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootJwtAuthenticationApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtAuthenticationApplication.class, args);
		try {
			FirebaseApp.getInstance();
		} catch (IllegalStateException ex) {
	        try {
//	        	FileInputStream serviceAccount =
//						  new FileInputStream("etutor-firebase-adminsdk.json");
	        	// new ClassPathResource("etutor-firebase-adminsdk.json").getInputStream()
				FirebaseOptions options = new FirebaseOptions.Builder()
						  .setCredentials(GoogleCredentials.fromStream(AdminSDKGenerator.getAdminSDKInputStream()))
						  .setDatabaseUrl("https://etutor-app-eb089.firebaseio.com")
						  .build();
				FirebaseApp.initializeApp(options);
				System.out.println("Firebase has been init");
	    	}catch(Exception e) {
	    		System.out.println(e.getMessage());
	    	}
		}
    }

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SlotRepository slotRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        Role tutorRole = new Role(RoleName.ROLE_TUTOR);
        Role studentRole = new Role(RoleName.ROLE_STUDENT);

        TransactionType sendTransactionType = new TransactionType(Constants.SEND);
        TransactionType receiveTransactionType = new TransactionType(Constants.RECEIVE);
        TransactionType topupTransactionType = new TransactionType(Constants.TOPUP);
        TransactionType withdrawalTransactionType = new TransactionType(Constants.WWITHDRAWAL);
        TransactionType paymentTransactionType = new TransactionType(Constants.PAYMENT);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(adminRole);
        roleSet.add(tutorRole);
        roleSet.add(studentRole);
        Account eTutorAccount = new Account(Constants.ETUTOR_EMAIL, encoder.encode(Constants.ETUTOR_PASSWORD), roleSet);

        roleRepository.save(adminRole);
        roleRepository.save(studentRole);
        roleRepository.save(tutorRole);

        transactionTypeRepository.save(receiveTransactionType);
        transactionTypeRepository.save(sendTransactionType);
        transactionTypeRepository.save(topupTransactionType);
        transactionTypeRepository.save(withdrawalTransactionType);

        accountRepository.save(eTutorAccount);

        List<Slot> slotList = new ArrayList<>();
        List<String> date = new ArrayList<>();
        date.add(Constants.SLOT_MONDAY);
        date.add(Constants.SLOT_TUESDAY);
        date.add(Constants.SLOT_WEDNESDAY);
        date.add(Constants.SLOT_THURSDAY);
        date.add(Constants.SLOT_FRIDAY);
        date.add(Constants.SLOT_SATURDAY);
        date.add(Constants.SLOT_SUNDAY);

        for (String dayInWeek: date) {
            double init = 0;
            for (int i = 1; i <= 16; i++) {
                String name = dayInWeek + "_" + i;
                double st = init;
                double et = st + 1.5;
                Slot slot = new Slot(name, st, et, dayInWeek);
                slotList.add(slot);
                System.out.println("Slot " + i + ":" + slot.getName() + "-" + slot.getStartTime() + "-" + slot.getEndTime() + "-" + slot.getDayInWeek());
                init = et;
            }
        }
        slotRepository.saveAll(slotList);
    }
}

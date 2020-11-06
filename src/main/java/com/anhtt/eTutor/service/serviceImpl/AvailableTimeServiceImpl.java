package com.anhtt.eTutor.service.serviceImpl;

import com.anhtt.eTutor.dto.AvailableTimeDTO;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.AvailableTime;
import com.anhtt.eTutor.model.Tutor;
import com.anhtt.eTutor.repository.AccountRepository;
import com.anhtt.eTutor.repository.AvailableTimeRepository;
import com.anhtt.eTutor.repository.TutorRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.AvailableTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AvailableTimeServiceImpl implements AvailableTimeService {

    @Autowired
    private AvailableTimeRepository availableTimeRepository;

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private AccountRepository accountRepository;
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
    @PreAuthorize("hasRole('ROLE_TUTOR')")
    public AvailableTimeDTO insert(List<String> slotIdList) {
        String slots = "";
        for (String slotId: slotIdList) {
            slots += (slotId + ",");
        }
        Account account = accountRepository.findAccountByEmail(getEmailByToken());
        Tutor tutor = tutorRepository.findByTutorAccount(account);
        AvailableTime availableTime = new AvailableTime();
        availableTime.setTutor(tutor);
        availableTime.setSlotInWeek(slots);
        availableTime = availableTimeRepository.save(availableTime);
        if(availableTime != null) {
            AvailableTimeDTO result = new AvailableTimeDTO();
            result.setId(availableTime.getId());
            result.setEmail(account.getEmail());
            result.setSlotInWeek(slots);
            result.setDeleted(availableTime.isDeleted());
            return  result;
        }
        return null;
    }
}

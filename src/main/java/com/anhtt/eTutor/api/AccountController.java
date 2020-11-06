package com.anhtt.eTutor.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.repository.UserRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    JwtProvider jwtProvider;

    public String getToken() {
        String[] header = request.getHeader("Authorization").split(" ");
        String token = header[header.length - 1];
        String email = jwtProvider.getEmailFromJwtToken(token);
        return email;
    }

    /**
     * Get account's detail.
     *
     * @return the entity
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Account> getUserDetail() {
        final Account account = userRepo.findByEmail(getToken());
        return ResponseEntity.ok(account);
    }

    /**
     * Update account's email response entity.
     *
     * @param accountDetails the account's details
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity<Account> changePassword(@Valid @RequestBody Account accountDetails) {
        Account account = userRepo.findByEmail(getToken());
        account.setPassword(accountDetails.getPassword());
        final Account updatedEmail = userRepo.save(account);
        return ResponseEntity.ok(updatedEmail);
    }

    @GetMapping("/balance")
    public ResponseEntity<Long> getBalance() {
        Account account = userRepo.findByEmail(getToken());
        return ResponseEntity.ok(account.getBalance());
    }
}
package com.anhtt.eTutor.auth;

import com.anhtt.eTutor.message.request.LoginForm;
import com.anhtt.eTutor.message.request.SignUpForm;
import com.anhtt.eTutor.message.response.JwtResponse;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.model.Role;
import com.anhtt.eTutor.model.RoleName;
import com.anhtt.eTutor.repository.RoleRepository;
import com.anhtt.eTutor.repository.UserRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.utils.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    EmailSender emailSending;

    @GetMapping("/expiration")
    public ResponseEntity<Void> checkExpiration() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest)
            throws AddressException, MessagingException, IOException {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Account user = new Account(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getBalance());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
            case "admin":
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(adminRole);
                break;
            case "tutor":
                Role tutorRole = roleRepository.findByName(RoleName.ROLE_TUTOR)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(tutorRole);
                break;
            default:
                Role userRole = roleRepository.findByName(RoleName.ROLE_STUDENT)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(userRole);
            }
        });

        user.setRoles(roles);
        emailSending.sendEmail(signUpRequest.getEmail());
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}
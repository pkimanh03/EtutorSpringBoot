package com.anhtt.eTutor.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhtt.eTutor.notification.dto.PushNotificationRequestDTO;
import com.anhtt.eTutor.notification.model.PushNotificationRequest;
import com.anhtt.eTutor.notification.model.PushNotificationResponse;
import com.anhtt.eTutor.notification.service.PushNotificationService;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/notification")
public class PushNotificationController {
	
	private PushNotificationService pushNotificationService;

	public PushNotificationController(PushNotificationService pushNotificationService) {
		super();
		this.pushNotificationService = pushNotificationService;
	}
	

	
	@PostMapping("/data")
	public ResponseEntity<?> sendDataNotification(@RequestBody PushNotificationRequestDTO request) {
		pushNotificationService.sendPushNotification(request);
		return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getNotification(@PathVariable String id) {
		return ResponseEntity.ok(pushNotificationService.getNotiByGUID(id));
	}
	
	
	
}

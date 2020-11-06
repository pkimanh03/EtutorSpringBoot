package com.anhtt.eTutor.notification.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.NotificationMapper;
import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.notification.dto.PushNotificationRequestDTO;
import com.anhtt.eTutor.notification.dto.PushNotificationRequestShowDTO;
import com.anhtt.eTutor.notification.firebase.FCMService;
import com.anhtt.eTutor.notification.model.PushNotificationRequest;
import com.anhtt.eTutor.notification.model.PushRequest;
import com.anhtt.eTutor.notification.repository.NotificationRepository;
import com.anhtt.eTutor.repository.AccountRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.sun.mail.imap.ResyncData;

@Service
public class PushNotificationService {

	@Value("#{${app.notifications.defaults}}")
	private Map<String, String> defaults;
	
	private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
	
	private FCMService fcmService;
	
	@Autowired
	private NotificationRepository notiRepo;
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	NotificationMapper notiMapper;

	public PushNotificationService(FCMService fcmService) {
		super();
		this.fcmService = fcmService;
	}
	
	
	public void sendPushNotification(PushNotificationRequestDTO request) {
		try {
			fcmService.sendMessage(getSamplePayloadData(), request);
			
			PushRequest result = new PushRequest();
			result.setBody(request.getBody());
			result.setTitle(request.getTitle());
			result.setTopic(request.getTopic());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			result.setCreateAt(time);
			
			notiRepo.save(result);
		}catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
	}
	
	private PushNotificationRequest getSamplePushNotification() {
		PushNotificationRequest request = new PushNotificationRequest(defaults.get("title"),
				defaults.get("message"),
				defaults.get("topic"));
		return request;
	}
	
	private Map<String, String> getSamplePayloadData(){
		Map<String, String> pushData = new HashMap<>();
		pushData.put("click_action", "FLUTTER_NOTIFICATION_CLICK");
		
		return pushData;
	}
	

	
//	public List<PushNotificationRequestDTO> getNoti(){
//		
//		List<PushRequest> result = notiRepo.findAll();
//		List<PushNotificationRequestDTO> list = notiMapper.toNotiDTO(result);
//		return list;
//	}
	
	public List<PushNotificationRequestShowDTO> getNotiByGUID(String id){
		List<PushRequest> result= notiRepo.findNotiByTopic(id);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		
		List<PushNotificationRequestShowDTO> list = notiMapper.toNotiDTO(result);
		for(int i = 0; i<list.size(); i++) {
			list.get(i).setCreate(simpleDateFormat.format(result.get(i).getCreateAt()));
		}
		return list;
		
	}
}

package com.anhtt.eTutor.notification.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.anhtt.eTutor.AdminSDKGenerator;
import com.anhtt.eTutor.notification.dto.PushNotificationRequestDTO;
import com.anhtt.eTutor.notification.model.PushNotificationRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

@Service
public class FCMService {
	private Logger logger = LoggerFactory.getLogger(FCMService.class);
	
	private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequestDTO request){
		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
		return Message.builder()
				.setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
				.setNotification(new Notification(request.getTitle(), request.getBody()));
		
	}
	
	private AndroidConfig getAndroidConfig(String topic) {
		return AndroidConfig.builder()
				.setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
				.setPriority(AndroidConfig.Priority.HIGH)
				.setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
						.setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
	}
	
	private ApnsConfig getApnsConfig(String topic) {
		return ApnsConfig.builder().setAps(
				Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
	}
	
	private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException{
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
		return FirebaseMessaging.getInstance().sendAsync(message).get();
	}
	
	
	
	private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequestDTO request) {
		return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic()).build();
	}
	
	public void sendMessage(Map<String,String>data, PushNotificationRequestDTO request) throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithData(data, request);
		String response = sendAndGetResponse(message);
		logger.info("Snd message with data. Topic: " + request.getTopic());
	}
	
	
}

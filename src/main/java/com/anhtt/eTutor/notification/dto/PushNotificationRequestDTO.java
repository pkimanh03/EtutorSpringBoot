package com.anhtt.eTutor.notification.dto;

import java.sql.Timestamp;

public class PushNotificationRequestDTO {
	
	private String title;

	
	private String body;

	
	private String topic;


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	


	public PushNotificationRequestDTO(String title, String body, String topic) {
		super();
		this.title = title;
		this.body = body;
		this.topic = topic;
	}


	public PushNotificationRequestDTO() {
		super();
	}
	
	


}

package com.anhtt.eTutor.notification.dto;

import java.sql.Timestamp;

public class PushNotificationRequestShowDTO {
	private String title;
	private String body;
	private String topic;
	private Timestamp createAt;
	private String create;
	public PushNotificationRequestShowDTO() {
		super();
	}
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
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	
	
	
	
}

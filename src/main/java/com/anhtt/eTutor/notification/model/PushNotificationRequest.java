package com.anhtt.eTutor.notification.model;

import com.anhtt.eTutor.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TBL_Notification")
public class PushNotificationRequest {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
	@JsonIgnore
	private UUID id;

	@Column(name = "title")
	private String title;

	@Column(name = "message")
	private String message;

	@Column(name = "topic")
	private String topic;

	@Column(name = "token")
	private String token;
		
	@ManyToOne
	@JoinColumn(name = "accountId", referencedColumnName = "id")
	@JsonDeserialize
	private Account account;

	public PushNotificationRequest(String title, String message, String topic) {
		super();
		this.title = title;
		this.message = message;
		this.topic = topic;
	}

	public PushNotificationRequest(String title, String message, String topic, String token, Account account) {
		this.title = title;
		this.message = message;
		this.topic = topic;
		this.token = token;
		this.account = account;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public PushNotificationRequest() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}

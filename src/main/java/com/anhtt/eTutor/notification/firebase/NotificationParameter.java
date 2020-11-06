package com.anhtt.eTutor.notification.firebase;

public enum NotificationParameter {
	SOUND("default"),
	COLOR("#3F51B5");
	
	private String value;

	private NotificationParameter(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}

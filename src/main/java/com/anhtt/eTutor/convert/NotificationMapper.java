package com.anhtt.eTutor.convert;

import java.util.List;

import org.mapstruct.Mapper;

import com.anhtt.eTutor.notification.dto.PushNotificationRequestDTO;
import com.anhtt.eTutor.notification.dto.PushNotificationRequestShowDTO;
import com.anhtt.eTutor.notification.model.PushNotificationRequest;
import com.anhtt.eTutor.notification.model.PushRequest;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	List<PushNotificationRequestShowDTO> toNotiDTO(List<PushRequest> notiList);

}

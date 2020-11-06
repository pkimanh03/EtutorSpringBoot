package com.anhtt.eTutor.notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anhtt.eTutor.model.Account;
import com.anhtt.eTutor.notification.model.PushNotificationRequest;
import com.anhtt.eTutor.notification.model.PushRequest;

@Repository
public interface NotificationRepository extends JpaRepository<PushRequest, UUID>{
	
	@Query(value = "SELECT * FROM notification WHERE topic like %:topic% ORDER BY create_at DESC", nativeQuery = true)
	List<PushRequest> findNotiByTopic(@Param("topic") String topic);
}

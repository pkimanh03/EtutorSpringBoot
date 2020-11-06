package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.AvailableTimeDTO;
import com.anhtt.eTutor.model.AvailableTime;

import java.util.List;

public interface AvailableTimeService {
    AvailableTimeDTO insert(List<String> slotIdList);
}

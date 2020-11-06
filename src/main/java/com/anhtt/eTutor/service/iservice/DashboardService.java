package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.DashboardResponseObject;

import java.util.List;

public interface DashboardService {
    List<DashboardResponseObject> getDataForDashboard();
}

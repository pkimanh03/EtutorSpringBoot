package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.DashboardResponseObject;
import com.anhtt.eTutor.service.iservice.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<List<DashboardResponseObject>> getDataForDashboard() {
        List<DashboardResponseObject> result = dashboardService.getDataForDashboard();
        return ResponseEntity.ok(result);
    }
}

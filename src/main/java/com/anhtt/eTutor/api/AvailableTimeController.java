package com.anhtt.eTutor.api;

import com.anhtt.eTutor.dto.AvailableTimeDTO;
import com.anhtt.eTutor.service.iservice.AvailableTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://api.etutor.top")
@RequestMapping("/api/available-time")
public class AvailableTimeController {

    @Autowired
    private AvailableTimeService availableTimeService;

    @PostMapping
    public ResponseEntity<AvailableTimeDTO> insert(@RequestBody List<String> listSlotIds) {
        AvailableTimeDTO result = availableTimeService.insert(listSlotIds);
        return ResponseEntity.ok(result);
    }
}

package com.anhtt.eTutor.service.iservice;

import com.anhtt.eTutor.dto.DashboardResponseObject;
import com.anhtt.eTutor.dto.MajorsDTO;
import com.anhtt.eTutor.dto.MajorsInsertDTO;

import java.util.List;
import java.util.UUID;

public interface MajorsService {
    List<MajorsDTO> getAll();
    MajorsDTO insert(MajorsInsertDTO majorsInsertDTO);
    MajorsDTO update(MajorsDTO majorsDTO);
    void delete(UUID id);
    List<MajorsDTO> getMajorsMostRegis();
    List<MajorsDTO> getTop3MajorWithCourse();

    List<DashboardResponseObject> getCourseForEachMajors();
}

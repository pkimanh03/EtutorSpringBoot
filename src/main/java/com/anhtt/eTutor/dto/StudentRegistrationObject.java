package com.anhtt.eTutor.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class StudentRegistrationObject {
    private UUID registrationId;
//    private List<UUID> listSlotId;
    List<ClassHourInsertDTO> classHourInsertDTOList;

    public StudentRegistrationObject() {
    }

    public StudentRegistrationObject(UUID registrationId, List<UUID> listSlotId) {
        this.registrationId = registrationId;
//        this.listSlotId = listSlotId;
    }

    public List<ClassHourInsertDTO> getClassHourInsertDTOList() {
        return classHourInsertDTOList;
    }

    public void setClassHourInsertDTOList(List<ClassHourInsertDTO> classHourInsertDTOList) {
        this.classHourInsertDTOList = classHourInsertDTOList;
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(UUID registrationId) {
        this.registrationId = registrationId;
    }

//    public List<UUID> getListSlotId() {
//        return listSlotId;
//    }
//
//    public void setListSlotId(List<UUID> listSlotId) {
//        this.listSlotId = listSlotId;
//    }
}

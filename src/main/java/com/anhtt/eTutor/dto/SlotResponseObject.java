package com.anhtt.eTutor.dto;

import java.util.List;

public class SlotResponseObject {
    private String dayInWeek;
    private List<SlotDTO> slotDTOList;

    public SlotResponseObject() {
    }

    public SlotResponseObject(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public SlotResponseObject(String dayInWeek, List<SlotDTO> slotDTOList) {
        this.dayInWeek = dayInWeek;
        this.slotDTOList = slotDTOList;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public List<SlotDTO> getSlotDTOList() {
        return slotDTOList;
    }

    public void setSlotDTOList(List<SlotDTO> slotDTOList) {
        this.slotDTOList = slotDTOList;
    }
}

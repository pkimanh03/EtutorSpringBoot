package com.anhtt.eTutor.dto;

import java.util.List;
import java.util.UUID;

public class DashboardResponseObject {
    private UUID majorsId;
    private String title;
    private List<DashboardItemObject> dashboardItemObjectList;

    public DashboardResponseObject() {
    }

    public DashboardResponseObject(String title, List<DashboardItemObject> dashboardItemObjectList) {
        this.title = title;
        this.dashboardItemObjectList = dashboardItemObjectList;
    }

    public UUID getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(UUID majorsId) {
        this.majorsId = majorsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DashboardItemObject> getDashboardItemObjectList() {
        return dashboardItemObjectList;
    }

    public void setDashboardItemObjectList(List<DashboardItemObject> dashboardItemObjectList) {
        this.dashboardItemObjectList = dashboardItemObjectList;
    }
}

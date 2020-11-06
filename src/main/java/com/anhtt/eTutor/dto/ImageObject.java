package com.anhtt.eTutor.dto;

public class ImageObject {
    private String data;
    private String name;

    public ImageObject(String data, String name) {
        this.data = data;
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

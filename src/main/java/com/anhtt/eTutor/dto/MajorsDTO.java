package com.anhtt.eTutor.dto;

import java.util.Collection;
import java.util.UUID;

import com.anhtt.eTutor.model.Course;

public class MajorsDTO {
    private UUID id;
    private String name;
    private String description;
    private Collection<CourseDTO> courseCollection;

    public MajorsDTO() {
    }

    

    public MajorsDTO(String name, String description, Collection<CourseDTO> courseCollection) {
		super();
		this.name = name;
		this.description = description;
		this.courseCollection = courseCollection;
	}
    
    



	public MajorsDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}



	public MajorsDTO(UUID id, String name, String description, Collection<CourseDTO> courseCollection) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.courseCollection = courseCollection;
	}
	
	



	public MajorsDTO(UUID id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}



	public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



	public Collection<CourseDTO> getCourseCollection() {
		return courseCollection;
	}



	public void setCourseCollection(Collection<CourseDTO> courseCollection) {
		this.courseCollection = courseCollection;
	}



	
    
    
}

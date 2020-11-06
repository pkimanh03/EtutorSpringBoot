package com.anhtt.eTutor.service.serviceImpl;

import com.anhtt.eTutor.dto.*;
import com.anhtt.eTutor.convert.CourseMapper;
import com.anhtt.eTutor.convert.MajorsMapper;
import com.anhtt.eTutor.model.Course;
import com.anhtt.eTutor.model.Majors;
import com.anhtt.eTutor.repository.CourseRepository;
import com.anhtt.eTutor.repository.MajorRepository;
import com.anhtt.eTutor.service.iservice.MajorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MajorsServiceImpl implements MajorsService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private MajorsMapper majorsMapper;
    
    @Autowired
    private CourseRepository courseRepo;
    
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<MajorsDTO> getAll() {
        List<Majors> majorsList = majorRepository.findByisDeletedFalse();
        List<MajorsDTO> result = majorsMapper.toMajorsDtoList(majorsList);
        return result;
    }

    @Override
    public MajorsDTO insert(MajorsInsertDTO majorsInsertDTO) {
        Majors majors = majorsMapper.toMajorsI(majorsInsertDTO);
        majors = majorRepository.save(majors);
        MajorsDTO result = majorsMapper.toMajorsDto(majors);
        return result;
    }

    @Override
    public MajorsDTO update(MajorsDTO majorsDTO) {
        Optional<Majors> majors = majorRepository.findById(majorsDTO.getId());
        if(majors.get() == null) {
            return null;
        } else {
            Majors updateMajors = majorsMapper.toMajors(majorsDTO);
            updateMajors = majorRepository.save(updateMajors);
            majorsDTO = majorsMapper.toMajorsDto(updateMajors);
            return majorsDTO;
        }

    }

    @Override
    public void delete(UUID id) {
        majorRepository.deleteById(id);
    }

	@Override
	public List<MajorsDTO> getMajorsMostRegis() {
		List<MajorsDTO> listMajor = majorRepository.getAllMajors();
		for(int i = 0; i < listMajor.size(); i ++) {
			MajorsDTO dto = listMajor.get(i);
			Majors m = majorsMapper.toMajors(dto);
			List<Course> listCourse = courseRepo.findTop5Bymajors(m);
			List<CourseDTO> result = courseMapper.toCourseDtoList(listCourse);
			listMajor.get(i).setCourseCollection(result);
		}

		return listMajor;
	}

	@Override
	public List<MajorsDTO> getTop3MajorWithCourse() {
		List<Majors> listMajor5= majorRepository.findTop3Majors();
		List<MajorsDTO> listMajor5dto = majorsMapper.toMajorsDtoList(listMajor5);
		for(int i = 0; i < listMajor5dto.size(); i++) {
			MajorsDTO dto = listMajor5dto.get(i);
			Majors m = majorsMapper.toMajors(dto);
			List<Course> listCourse = courseRepo.findTop5Bymajors(m);
			List<CourseDTO> result = courseMapper.toCourseDtoList(listCourse);
			listMajor5dto.get(i).setCourseCollection(result);
		}
		
		return listMajor5dto;
	}

    @Override
    public List<DashboardResponseObject> getCourseForEachMajors() {
        List<DashboardResponseObject> result = new ArrayList<>();

        List<Majors> majorsList = majorRepository.findAll();

        result = majorsList.stream().map(majors -> {
            DashboardResponseObject responseObject = new DashboardResponseObject();

            responseObject.setMajorsId(majors.getId());
            responseObject.setTitle(majors.getName());

//            List<Course> courseList = courseRepo.findDistinctByNameAndMajors(majors);
//            List<DashboardItemObject> itemObjectList = courseList.stream().map(course -> {
//                DashboardItemObject itemObject = new DashboardItemObject();
//                itemObject.setName(course.getName());
//                itemObject.setDescription(course.getDescription());
//                itemObject.setImage(course.getImage());
//                return itemObject;
//            }).collect(Collectors.toList());
//            responseObject.setDashboardItemObjectList(itemObjectList);

            List<String> courseNames = courseRepo.find5DistinctCourseNameByMajors(majors.getId());
            List<DashboardItemObject> itemObjectList = new ArrayList<>();
            for (String name:courseNames) {
                Course course = courseRepo.findCourseByName(name);
                DashboardItemObject itemObject = new DashboardItemObject();
                itemObject.setName(course.getName());
                itemObject.setImage(course.getImage());
                itemObject.setDescription(course.getDescription());

                itemObjectList.add(itemObject);
            }
            responseObject.setDashboardItemObjectList(itemObjectList);
            return responseObject;
        }).collect(Collectors.toList());

        return result;
    }


}

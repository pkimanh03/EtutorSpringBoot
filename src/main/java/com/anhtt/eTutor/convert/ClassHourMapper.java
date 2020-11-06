package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.ClassHourDTO;
import com.anhtt.eTutor.dto.ClassHourInsertDTO;
import com.anhtt.eTutor.model.ClassHour;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassHourMapper {
    ClassHourDTO toClassHourDto(ClassHour classHour);
    List<ClassHourDTO> toClassHourDtoList(List<ClassHour> classHourList);

    ClassHourInsertDTO toClassHourInsertDto(ClassHour classHour);
    List<ClassHourInsertDTO> toClassHourInsertDtoList(List<ClassHour> classHourList);

    ClassHour toClassHour(ClassHourDTO classHourDTO);
    ClassHour toClassHourI(ClassHourInsertDTO classHourInsertDTO);
}

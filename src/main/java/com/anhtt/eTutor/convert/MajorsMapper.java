package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.MajorsDTO;
import com.anhtt.eTutor.dto.MajorsInsertDTO;
import com.anhtt.eTutor.model.Majors;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MajorsMapper {
    MajorsDTO toMajorsDto(Majors majors);
    List<MajorsDTO> toMajorsDtoList(List<Majors> majorsList);

    MajorsInsertDTO toMajorsInsertDto(Majors majors);
    List<MajorsInsertDTO> toMajorsInsertDtoList(List<Majors> majorsList);

    Majors toMajors(MajorsDTO majorsDTO);
    Majors toMajorsI(MajorsInsertDTO majorsInsertDTO);
}

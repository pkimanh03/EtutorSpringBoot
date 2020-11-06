package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.StandardDTO;
import com.anhtt.eTutor.dto.StandardInsertDTO;
import com.anhtt.eTutor.model.Standard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StandardMapper {
    StandardDTO toStandardDto(Standard standard);
    List<StandardDTO> toStandardDtoList(List<Standard> standardList);

    StandardInsertDTO toStandardInsertDto(Standard standard);
    List<StandardInsertDTO> toStandardInsertDtoList(List<Standard> standardList);

    Standard toStandard(StandardDTO standardDTO);
    Standard tStandardI(StandardInsertDTO standardInsertDTO);
}

package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.TutorInsertDTO;
import com.anhtt.eTutor.dto.TutorShowDTO;
import com.anhtt.eTutor.dto.TutorShowProfileDTO;
import com.anhtt.eTutor.dto.TutorUpdateDTO;
import com.anhtt.eTutor.model.Tutor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TutorMapper {
    TutorInsertDTO toTutorInsertDto(Tutor tutor);
    List<TutorInsertDTO> toTutorInsertDtoList(List<Tutor> tutorList);

    TutorShowDTO toTutorShowDto(Tutor tutor);
    List<TutorShowDTO> toTutorShowDtoList(List<Tutor> tutorList);

    TutorUpdateDTO toTutorUpdateDto(Tutor tutor);
    List<TutorUpdateDTO> toTutorUpdateDtoList(List<Tutor> tutorList);

    Tutor toTutorInsert(TutorInsertDTO tutorInsertDTO);
    Tutor toTutorUpdate(TutorUpdateDTO tutorUpdateDTO);
    Tutor toTutorShow(TutorShowDTO tutorShowDTO);
    
    TutorShowProfileDTO toTutorShowProfile(Tutor tutor);
}

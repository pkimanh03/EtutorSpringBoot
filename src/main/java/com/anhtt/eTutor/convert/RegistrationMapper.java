package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.RegistrationDTO;
import com.anhtt.eTutor.dto.RegistrationInsertDTO;
import com.anhtt.eTutor.model.Registration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationDTO toRegistrationDto(Registration registration);
    List<RegistrationDTO> toRegistrationDtoList(List<Registration> registrationList);

    RegistrationInsertDTO toRegistrationInsertDto(Registration registration);
    List<RegistrationInsertDTO> toRegistrationInsertDtoList(List<Registration> registrationList);

    Registration toRegistration(RegistrationDTO registrationDTO);
    Registration toRegistrationI(RegistrationInsertDTO registrationInsertDTO);
}

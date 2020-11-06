//
//package com.anhtt.eTutor.KA;
//
//import com.anhtt.eTutor.dto.TutorDTO;
//import com.anhtt.eTutor.model.Tutor;
//import org.mapstruct.Mapper;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface TutorMapper {
////    public TutorDTO toDto(Tutor tutor) {
////        if(tutor == null)
////            return null;
////        TutorDTO dto = new TutorDTO();
////        dto.setId(tutor.getId());
////        dto.setFullname(tutor.getFullname());
////        dto.setAge(tutor.getAge());
////        dto.setAddress(tutor.getAddress());
////        dto.setDrivingLicenseImg(tutor.getDrivingLicenseImg());
////        dto.setIdCardImg(tutor.getIdCardImg());
////        dto.setAvatar(tutor.getAvatar());
////        return dto;
////    }
////
////    public Tutor toTutor(TutorDTO tutorDTO) {
////        if(tutorDTO == null)
////            return null;
////        Tutor entity = new Tutor();
////        entity.setId(tutorDTO.getId());
////        entity.setFullname(tutorDTO.getFullname());
////        entity.setAge(tutorDTO.getAge());
////        entity.setAddress(tutorDTO.getAddress());
////        entity.setDrivingLicenseImg(tutorDTO.getDrivingLicenseImg());
////        entity.setIdCardImg(tutorDTO.getIdCardImg());
////        entity.setAvatar(tutorDTO.getAvatar());
////        return entity;
////    }
////
////    public List<TutorDTO> toDtos(List<Tutor> tutors) {
////        if (tutors.isEmpty())
////            return null;
////        List<TutorDTO> tutorDtos = new ArrayList<>();
////        for (Tutor tutor: tutors) {
////            tutorDtos.add(toDto(tutor));
////        }
////        return tutorDtos;
////    }
//    TutorDTO toDto(Tutor tutor);
//
//    List<TutorDTO> toDtos(List<Tutor> tutors);
//
//    Tutor toTutor(TutorDTO tutorDTO);
//}

package com.anhtt.eTutor.convert;


import com.anhtt.eTutor.dto.AttendanceDTO;
import com.anhtt.eTutor.dto.AttendanceInsertDTO;
import com.anhtt.eTutor.model.Attendance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    AttendanceDTO toAttendanceDto(Attendance attendance);
    List<AttendanceDTO> toAttendanceDtoList(List<Attendance> attendanceList);

    AttendanceInsertDTO toAttendanceInsertDto(Attendance attendance);
    List<AttendanceInsertDTO> toAttendanceInsertDtoList(List<Attendance> attendanceList);

    Attendance toAttendance(AttendanceDTO attendanceDTO);
    Attendance toAttendanceI(AttendanceInsertDTO attendanceInsertDTO);
}

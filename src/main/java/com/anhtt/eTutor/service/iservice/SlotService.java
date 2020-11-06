package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.anhtt.eTutor.dto.AttendanceDTO;
import com.anhtt.eTutor.dto.SlotDTO;
import com.anhtt.eTutor.dto.SlotInsertDTO;
import com.anhtt.eTutor.dto.SlotResponseObject;

public interface SlotService {
	List<SlotResponseObject> retrieveAllSlot();
	SlotInsertDTO saveSlot(SlotInsertDTO slot);
	SlotInsertDTO updateSlot(SlotInsertDTO slot, UUID id);
	Optional<SlotDTO> findByIdSlot(Long id);
	void deleteSlot(UUID id);

}

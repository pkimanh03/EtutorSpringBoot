package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.anhtt.eTutor.dto.SlotDTO;
import com.anhtt.eTutor.dto.StandardDTO;
import com.anhtt.eTutor.dto.StandardInsertDTO;

public interface StandarService {
	List<StandardDTO> getAll();
	StandardDTO saveStandar(StandardDTO att);
	StandardDTO updateStandar(StandardDTO att);
	void deleteStandar(UUID id);
	StandardInsertDTO getStandardByTutor(UUID id);
}

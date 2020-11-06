package com.anhtt.eTutor.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.StandardMapper;
import com.anhtt.eTutor.dto.StandardDTO;
import com.anhtt.eTutor.dto.StandardInsertDTO;
import com.anhtt.eTutor.model.Majors;
import com.anhtt.eTutor.model.Standard;
import com.anhtt.eTutor.model.Tutor;
import com.anhtt.eTutor.repository.MajorRepository;
import com.anhtt.eTutor.repository.StandardRepository;
import com.anhtt.eTutor.repository.TutorRepository;
import com.anhtt.eTutor.security.jwt.JwtProvider;
import com.anhtt.eTutor.service.iservice.StandarService;

@Service
public class StandarServiceImpl implements StandarService{
	
	@Autowired
	StandardRepository standRepo;
	
	@Autowired
	StandardMapper standMapper;
	
	@Autowired
	MajorRepository majorRepo;
	
	@Autowired
	TutorRepository tuRepo;
	
	

	@Override
	public List<StandardDTO> getAll() {
		List<Standard> result = standRepo.findByisDeletedFalse();
		return standMapper.toStandardDtoList(result);
	}

	@Override
	public StandardDTO saveStandar(StandardDTO stand) {
		Standard result = standMapper.toStandard(stand);
		Optional<Tutor> curTu = tuRepo.findById(stand.getTutorId());
		Optional<Majors> curMajor = majorRepo.findById(stand.getMajorsId());
		result.setMajors(curMajor.get());
		result.setTutor(curTu.get());
		standRepo.save(result);
		return standMapper.toStandardDto(result);
	}

	@Override
	public StandardDTO updateStandar(StandardDTO stand) {
		Standard result = standRepo.findById(stand.getId()).get();

		if(result!=null) {
			Standard s = standMapper.toStandard(stand);
			
			if(s.getCertificationImage() == null) {
				s.setCertificationImage(result.getCertificationImage());
			}
			if(s.getDescription() == null) {
				s.setDescription(result.getDescription());
			}
			if(s.getExpiredDate() == null) {
				s.setExpiredDate(result.getExpiredDate());
			}
			if(s.getIssueDate() == null) {
				s.setIssueDate(result.getIssueDate());
			}
			if(s.getName() == null) {
				s.setName(result.getName());
			}
		Optional<Majors> curMajor = majorRepo.findById(stand.getMajorsId());
		Optional<Tutor> curTu = tuRepo.findById(stand.getTutorId());
		s.setMajors(curMajor.get());
		s.setTutor(curTu.get());
		standRepo.save(s);
		return stand;
		}
		return null;
	}

	@Override
	public void deleteStandar(UUID id) {
		standRepo.deleteById(id);
	}

	@Override
	public StandardInsertDTO getStandardByTutor(UUID id) {
		return standRepo.getStandardByTutor(id);
	}

	
	
	
}

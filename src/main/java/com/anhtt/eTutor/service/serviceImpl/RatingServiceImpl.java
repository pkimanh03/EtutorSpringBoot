	package com.anhtt.eTutor.service.serviceImpl;

	import java.util.List;
	import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.anhtt.eTutor.convert.RatingMapper;
import com.anhtt.eTutor.dto.RatingDTO;
	import com.anhtt.eTutor.model.Rating;
	import com.anhtt.eTutor.model.Student;
	import com.anhtt.eTutor.model.Tutor;
	import com.anhtt.eTutor.repository.RatingRepository;
	import com.anhtt.eTutor.repository.StudentRepository;
	import com.anhtt.eTutor.repository.TutorRepository;
	import com.anhtt.eTutor.service.iservice.RatingService;

	@Service
	public class RatingServiceImpl implements RatingService{
		
		@Autowired
		RatingRepository raRepo;
		
		@Autowired
		RatingMapper raMapper;
		
		@Autowired
		TutorRepository tuRepo;
		
		@Autowired
		StudentRepository stuRepo;

		@Override
		public List<RatingDTO> getAll() {
			List<Rating> raList = raRepo.findByisDeletedFalse();
			return raMapper.toRatingDtoList(raList);
		}

		@Override
		public RatingDTO insert(RatingDTO ratingDTO) {
			Rating rating = raMapper.toRating(ratingDTO);
//			Optional<Student> curStu = stuRepo.findById(ratingDTO.get);
//			if (curStu == null) {
//				return null;
//			}
//			Optional<Tutor> curTu = tuRepo.findById(ratingDTO.getTutorId());
//			if(curTu == null) {
//				return null;
//			}
//			rating.setStudent(curStu.get());
//			rating.setTutor(curTu.get());
			raRepo.save(rating);
			return raMapper.toRatingDto(rating);
		}

		@Override
		public RatingDTO update(RatingDTO ratingDTO) {
			Optional<Rating> rating = raRepo.findById(ratingDTO.getId());
			if(rating!=null) {
//				Optional<Tutor> curTu = tuRepo.findById(ratingDTO.getTutorId());	
//				Optional<Student> curStu = stuRepo.findById(ratingDTO.getStudentId());
//				
				Rating a = raMapper.toRating(ratingDTO);
//				a.setTutor(curTu.get());
//				a.setStudent(curStu.get());
				raRepo.save(a);
				return ratingDTO;
			}
			return null;
		}

		@Override
		public void delete(UUID id) {
			raRepo.deleteById(id);
		}
		
		

	}

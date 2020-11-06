package com.anhtt.eTutor.service.iservice;

import java.util.List;
import java.util.UUID;

import com.anhtt.eTutor.dto.RatingDTO;

public interface RatingService {
	List<RatingDTO> getAll();
	RatingDTO insert(RatingDTO ratingDTO);
	RatingDTO update(RatingDTO ratingDTO);
    void delete(UUID id);
}

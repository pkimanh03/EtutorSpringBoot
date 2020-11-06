package com.anhtt.eTutor.convert;

import com.anhtt.eTutor.dto.RatingDTO;
import com.anhtt.eTutor.dto.RatingInsertDTO;
import com.anhtt.eTutor.model.Rating;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingDTO toRatingDto(Rating rating);
    List<RatingDTO> toRatingDtoList(List<Rating> ratingList);

    RatingInsertDTO toRatingInsertDto(Rating rating);
    List<RatingInsertDTO> toRatingInsertDtos(List<Rating> ratingList);

    Rating toRating(RatingDTO ratingDTO);
    Rating toRatingI(RatingInsertDTO ratingInsertDTO);
}

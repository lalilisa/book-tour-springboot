package com.ttcs.model.mapper;

import com.ttcs.domain.TourImage;
import com.ttcs.model.dto.TourImageDTO;

public class TourImageMapper {
	public static TourImageDTO tourImageDTO(TourImage tourImage) {
		TourImageDTO tmp = new TourImageDTO();
		tmp.setTourId(tourImage.getTour().getTourId());
		tmp.setUrl(tourImage.getUrl());
		return tmp;
	}
	
	public static TourImage toTourImageEntity(TourImageDTO tourImageDTO) {
		TourImage tmp = new TourImage();
		tmp.setUrl(tourImageDTO.getUrl());
		return tmp;
	}
}

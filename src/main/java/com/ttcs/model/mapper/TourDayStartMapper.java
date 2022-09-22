package com.ttcs.model.mapper;

import com.ttcs.domain.TourDayStart;
import com.ttcs.model.dto.TourDayStartDTO;

public class TourDayStartMapper {
	public static TourDayStartDTO tourDayStartDTO(TourDayStart tourDayStart) {
		TourDayStartDTO tmp = new TourDayStartDTO();
		tmp.setTourId(tourDayStart.getTour().getTourId());
		tmp.setDayStart(tourDayStart.getDayStart());
		return tmp;
	}
	
	public static TourDayStart tourDayStartEntity(TourDayStartDTO tourDayStartDTO) {
		TourDayStart tmp = new TourDayStart();
		tmp.setDayStart(tourDayStartDTO.getDayStart());
		return tmp;
	}
}

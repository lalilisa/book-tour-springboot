package com.ttcs.service;

import java.util.List;

import com.ttcs.domain.TourDayStart;
import com.ttcs.model.dto.TourDayStartDTO;

public interface TourDayStartService {

	List<TourDayStart> findByIdTour(Integer id);

	void save(Integer tourId, List<TourDayStartDTO> list);

}

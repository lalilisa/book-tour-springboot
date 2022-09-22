package com.ttcs.service;

import java.util.List;

import com.ttcs.domain.TourImage;
import com.ttcs.model.dto.TourImageDTO;

public interface TourImageService {

	List<TourImage> findByIdTour(Integer id);

	void save(Integer tourId, List<TourImageDTO> list);

}

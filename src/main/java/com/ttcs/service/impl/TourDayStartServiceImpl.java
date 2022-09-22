package com.ttcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttcs.domain.TourDayStart;
import com.ttcs.model.dto.TourDayStartDTO;
import com.ttcs.repository.TourDayStartRepository;
import com.ttcs.service.TourDayStartService;

@Service
public class TourDayStartServiceImpl implements TourDayStartService{
	@Autowired
	TourDayStartRepository tourDayStartRepository;

	public TourDayStartServiceImpl(TourDayStartRepository tourDayStartRepository) {
		super();
		this.tourDayStartRepository = tourDayStartRepository;
	}

	@Override
	public List<TourDayStart> findByIdTour(Integer id) {
		return tourDayStartRepository.findByIdTour(id);
	}

	@Override
	@Transactional
	public void save(Integer tourId, List<TourDayStartDTO> list) {
		tourDayStartRepository.deleteByTourId(tourId);
		for(TourDayStartDTO i : list) {
			tourDayStartRepository.insert(tourId,i.getDayStart());
		}
	}
}

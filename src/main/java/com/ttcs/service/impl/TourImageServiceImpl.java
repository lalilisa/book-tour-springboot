package com.ttcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttcs.domain.TourImage;
import com.ttcs.model.dto.TourImageDTO;
import com.ttcs.repository.TourImageRepository;
import com.ttcs.service.TourImageService;

@Service
public class TourImageServiceImpl implements TourImageService{
	@Autowired
	TourImageRepository tourImageRepository;

	public TourImageServiceImpl(TourImageRepository tourImageRepository) {
		super();
		this.tourImageRepository = tourImageRepository;
	}

	@Override
	public List<TourImage> findByIdTour(Integer id) {
		return tourImageRepository.findByIdTour(id);
	}
	
	@Override
	@Transactional
	public void save(Integer tourId, List<TourImageDTO> list) {
		tourImageRepository.deleteByTourId(tourId);
		for(TourImageDTO i : list) {
			tourImageRepository.insert(tourId, i.getUrl());;
		}
	}
}

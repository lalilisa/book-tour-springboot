package com.ttcs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ttcs.domain.Category;
import com.ttcs.domain.Tour;
import com.ttcs.model.dto.TourDTO;
import com.ttcs.model.mapper.TourMapper;
import com.ttcs.repository.CategoryRepository;
import com.ttcs.repository.TourRepository;
import com.ttcs.service.TourService;

@Service
public class TourServiceImpl implements TourService{
	@Autowired
	TourRepository tourRepository;
	@Autowired
	CategoryRepository categoryRepository;

	public TourServiceImpl(TourRepository tourRepository) {
		super();
		this.tourRepository = tourRepository;
	}

	@Override
	public List<Tour> findAll() {
		return tourRepository.findAll();
	}

	@Override
	public Page<Tour> findAll(Pageable pageable) {
		return tourRepository.findAll(pageable);
	}

	@Override
	public List<Tour> findAll(Sort sort) {
		return tourRepository.findAll(sort);
	}

	@Override
	public Optional<Tour> findById(Integer id) {
		return tourRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return tourRepository.existsById(id);
	}

	@Override
	public long count() {
		return tourRepository.count();
	}

	@Override
	public Tour getById(Integer id) {
		return tourRepository.getById(id);
	}

	@Override
	public List<Tour> findByHotSale() {
		List<Tour> list = tourRepository.findAll(Sort.by(Direction.DESC, "sale"));
		if (list.size()<=6) {
			return list;
		}
		return list.subList(0, 6);
	}
	
	@Override
	public List<Tour> findByRegionAndLimit(String region, Integer limit) {
		Page<Tour> list = tourRepository.findByRegionLike(region, PageRequest.of(0, 6));
		return list.getContent();
	}

	@Override
	public Page<Tour> findByRegionAndCategory(String region, Integer cateid, Pageable pageable) {
		return tourRepository.findByRegionAndCategory(region, cateid, pageable);
	}

	@Override
	public Page<Tour> findByStartLoc(String startLoc, Pageable pageable) {
		return tourRepository.findByStartLocLike(startLoc, pageable);
	}

	@Override
	public Page<Tour> findByCateId(Integer cateid, Pageable pageable) {
		return tourRepository.findByCateId(cateid, pageable);
	}

	@Override
	public Page<Tour> findByRegionPage(String region, Pageable pageable) {
		return tourRepository.findByRegionLike(region, pageable);
	}

	@Override
	public List<Tour> findByKey(String key) {
		return tourRepository.findByKey(key);
	}
	
	@Override
	public TourDTO save(TourDTO tourDTO) {
		Tour t = new Tour();
		if(tourDTO.getTourId() != 0) {
			Tour a = tourRepository.getById(tourDTO.getTourId());
			t = TourMapper.toTourEntity(tourDTO, a);
		}
		else {
			t = TourMapper.toTourEntity(tourDTO);
		}
		Category c = categoryRepository.getById(tourDTO.getCateId());
		t.setCategory(c);
		t = tourRepository.save(t);
		return TourMapper.totourDTO(t);
	}
	
	@Override
	public void delete(Integer tourId) {
		tourRepository.deleteById(tourId);
	}
	
}

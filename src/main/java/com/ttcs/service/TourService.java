package com.ttcs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ttcs.domain.Tour;
import com.ttcs.model.dto.TourDTO;

public interface TourService {

	TourDTO save(TourDTO tourDTO);

	List<Tour> findByKey(String key);

	Page<Tour> findByRegionPage(String region, Pageable pageable);

	Page<Tour> findByCateId(Integer cateid, Pageable pageable);

	Page<Tour> findByStartLoc(String startLoc, Pageable pageable);

	Page<Tour> findByRegionAndCategory(String region, Integer cateid, Pageable pageable);

	List<Tour> findByRegionAndLimit(String region, Integer limit);

	List<Tour> findByHotSale();

	Tour getById(Integer id);

	long count();

	boolean existsById(Integer id);

	Optional<Tour> findById(Integer id);

	List<Tour> findAll(Sort sort);

	Page<Tour> findAll(Pageable pageable);

	List<Tour> findAll();

	void delete(Integer tourId);

}

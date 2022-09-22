package com.ttcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttcs.domain.Activity;
import com.ttcs.model.dto.ActivityDTO;
import com.ttcs.repository.ActivityRepository;
import com.ttcs.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{
	@Autowired
	ActivityRepository activityRepository;

	public ActivityServiceImpl(ActivityRepository activityRepository) {
		super();
		this.activityRepository = activityRepository;
	}

	@Override
	public List<Activity> findByIdTour(Integer id) {
		return activityRepository.findByIdTour(id);
	}
	
	@Override
	@Transactional
	public void save(Integer tourId, List<ActivityDTO> list) {
		activityRepository.deleteByTourId(tourId);
		for(ActivityDTO i:list) {
			activityRepository.insert(tourId, i.getDay(), i.getTitle(), i.getAct());;
		}
	}
	
}

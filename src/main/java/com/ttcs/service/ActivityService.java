package com.ttcs.service;

import java.util.List;

import com.ttcs.domain.Activity;
import com.ttcs.model.dto.ActivityDTO;

public interface ActivityService {

	List<Activity> findByIdTour(Integer id);

	void save(Integer tourId, List<ActivityDTO> list);

}

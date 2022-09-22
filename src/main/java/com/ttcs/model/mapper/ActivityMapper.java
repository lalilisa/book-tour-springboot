package com.ttcs.model.mapper;

import com.ttcs.domain.Activity;
import com.ttcs.model.dto.ActivityDTO;

public class ActivityMapper {
	public static ActivityDTO toActivityDTO(Activity activity) {
		ActivityDTO tmp = new ActivityDTO();
		tmp.setTourId(activity.getTour().getTourId());
		tmp.setDay(activity.getDay());
		tmp.setTitle(activity.getTitle());
		tmp.setAct(activity.getAct());
		return tmp;
	}
	
	public static Activity activityEntity(ActivityDTO activityDTO) {
		Activity tmp = new Activity();
		tmp.setTitle(activityDTO.getTitle());
		tmp.setDay(activityDTO.getDay());
		tmp.setAct(activityDTO.getAct());
		return tmp;
	}
}

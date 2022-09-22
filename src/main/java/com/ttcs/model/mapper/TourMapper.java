package com.ttcs.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ttcs.domain.Activity;
import com.ttcs.domain.BookTour;
import com.ttcs.domain.Tour;
import com.ttcs.domain.TourDayStart;
import com.ttcs.domain.TourImage;
import com.ttcs.model.dto.ActivityDTO;
import com.ttcs.model.dto.BookTourDTO;
import com.ttcs.model.dto.TourDTO;
import com.ttcs.model.dto.TourDayStartDTO;
import com.ttcs.model.dto.TourImageDTO;

public class TourMapper {
	public static TourDTO totourDTO(Tour tour) {
		TourDTO tmp = new TourDTO();
		List<TourDayStartDTO> list1 = new ArrayList<>();
		List<TourImageDTO> list2 = new ArrayList<>();
		List<ActivityDTO> list3 = new ArrayList<>();
		List<BookTourDTO> list4 = new ArrayList<>();
		for(TourDayStart b : tour.getListDayStarts()) {
			list1.add(TourDayStartMapper.tourDayStartDTO(b));
		}
		for(TourImage b : tour.getListimgs()) {
			list2.add(TourImageMapper.tourImageDTO(b));
		}
		for(Activity b : tour.getListacts()) {
			list3.add(ActivityMapper.toActivityDTO(b));
		}
		for(BookTour b : tour.getListbooks()) {
			list4.add(BookTourMapper.toBookTourDTO(b));
		}
		tmp.setTourId(tour.getTourId());
		tmp.setTourName(tour.getTourName());
		tmp.setTourCost(tour.getTourCost());
		tmp.setTourDescription(tour.getTourDescription());
		tmp.setStartLoc(tour.getStartLoc());
		tmp.setSale(tour.getSale());
		tmp.setRegion(tour.getRegion());
		tmp.setDays(tour.getDays());
		tmp.setListDayStarts(list1);
		tmp.setListimgs(list2);
		tmp.setListacts(list3);
		tmp.setListbooks(list4);
		tmp.setCateId(tour.getCategory().getCateId());
		return tmp;
	}
	
	public static List<TourDTO> tolistTourDTO(List<Tour> tours){
		List<TourDTO> listDTO = new ArrayList<>();
		for(Tour t:tours) {
			listDTO.add(TourMapper.totourDTO(t));
		}
		return listDTO;
	}
	
	public static Tour toTourEntity(TourDTO tourdto) {
		Tour tmp = new Tour();
		tmp.setTourName(tourdto.getTourName());
		tmp.setTourCost(tourdto.getTourCost());
		tmp.setTourDescription(tourdto.getTourDescription());
		tmp.setStartLoc(tourdto.getStartLoc());
		tmp.setSale(tourdto.getSale());
		tmp.setRegion(tourdto.getRegion());
		tmp.setDays(tourdto.getDays());
		return tmp;
	}
	
	public static Tour toTourEntity(TourDTO tourdto, Tour tmp) {
		tmp.setTourName(tourdto.getTourName());
		tmp.setTourCost(tourdto.getTourCost());
		tmp.setTourDescription(tourdto.getTourDescription());
		tmp.setStartLoc(tourdto.getStartLoc());
		tmp.setSale(tourdto.getSale());
		tmp.setRegion(tourdto.getRegion());
		tmp.setDays(tourdto.getDays());
		return tmp;
	}
}

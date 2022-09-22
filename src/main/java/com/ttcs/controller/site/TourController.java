package com.ttcs.controller.site;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttcs.domain.Tour;
import com.ttcs.model.dto.TourDTO;
import com.ttcs.model.mapper.TourMapper;
import com.ttcs.service.ActivityService;
import com.ttcs.service.TourDayStartService;
import com.ttcs.service.TourImageService;
import com.ttcs.service.TourService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Tour Controller")
@RestController
@RequestMapping("/data")
public class TourController {
	@Autowired
	TourService tourService;
	@Autowired
	TourImageService tourImageService;
	@Autowired
	TourDayStartService tourDayStartService;
	@Autowired
	ActivityService activityService;
	
	@GetMapping(value="/hotsale", produces = "application/json")
	public List<TourDTO> getHotSaleTourDTO(){
		return TourMapper.tolistTourDTO(tourService.findByHotSale());
	}
	
	@GetMapping(value="/region", produces = "application/json")
	public List<TourDTO> getTourByRegionAndLimit(@RequestParam("name") String region,
			@RequestParam(name="limit") Integer limit){
		return TourMapper.tolistTourDTO(tourService.findByRegionAndLimit(region, limit));
	}
	
	@GetMapping(value="/fulllist", produces = "application/json")
	public List<TourDTO> getFullListTour(){
		List<Tour> list = tourService.findAll();
		return TourMapper.tolistTourDTO(list);
	}
	
	@GetMapping(value="/statistictour", produces = "application/json")
	public Integer statisticTour(){
		return tourService.findAll().size();
	}
	
	@GetMapping(value="/searchtour", produces = "application/json")
	public List<TourDTO> getTourByKey(@RequestParam(name="key", required = false, defaultValue = "") String key){
		List<Tour> list = tourService.findByKey(key);
		return TourMapper.tolistTourDTO(list);
	}
	
	@GetMapping(value="/pagetour", produces = "application/json")
	public Page<TourDTO> getTourByFilter(@RequestParam(name="page") Optional<Integer> page,
			@RequestParam(name="region", required = false, defaultValue = "all") String region,
			@RequestParam(name="cateid", required = false, defaultValue = "1") Integer cateid,
			@RequestParam(name="orderby", required = false, defaultValue = "1") Integer orderby){
		Sort sort = null;
		if (orderby==1) {
			sort = Sort.by(Direction.ASC, "tourCost");
		}
		else if (orderby==2) {
			sort = Sort.by(Direction.DESC, "tourCost");
		}
		else {
			sort = Sort.by(Direction.ASC, "tourId");
		}
		Page<Tour> list = null;
		if(region.equalsIgnoreCase("all") && cateid==1) {
			list = tourService.findAll(PageRequest.of(page.orElse(0), 10, sort));
		}
		else if(region.equalsIgnoreCase("all")) {
			list = tourService.findByCateId(cateid, PageRequest.of(page.orElse(0), 10, sort));
		}
		else if(cateid==1) {
			list = tourService.findByRegionPage(region, PageRequest.of(page.orElse(0), 10, sort));
		}
		else {
			list = tourService.findByRegionAndCategory(region, cateid, PageRequest.of(page.orElse(0), 10, sort));
		}
		return list.map(object -> TourMapper.totourDTO(object));
	}
	
	@GetMapping(value="/tour/{id}", produces = "application/json")
	public TourDTO getTourById(@PathVariable("id") Integer tourid) {
		return TourMapper.totourDTO(tourService.getById(tourid));
	}
	
	@PostMapping(value="/tour")
	public TourDTO createTour(@RequestBody TourDTO model) {
		TourDTO t = tourService.save(model);
		tourImageService.save(t.getTourId(), model.getListimgs());
		tourDayStartService.save(t.getTourId(), model.getListDayStarts());
		activityService.save(t.getTourId(), model.getListacts());
		return t;
	}
	
	@PutMapping(value = "/tour/{id}")
	public TourDTO updateTour(@RequestBody TourDTO model, @PathVariable("id") Integer tourId) {
		model.setTourId(tourId);
		TourDTO t = tourService.save(model);
		tourImageService.save(t.getTourId(), model.getListimgs());
		tourDayStartService.save(t.getTourId(), model.getListDayStarts());
		activityService.save(t.getTourId(), model.getListacts());
		return t;
	}
	
	@DeleteMapping(value="/tour/{id}")
	public void deleteTour(@PathVariable("id") Integer tourId) {
		tourService.delete(tourId);
	}
}

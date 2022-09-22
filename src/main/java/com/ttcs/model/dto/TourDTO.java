package com.ttcs.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourDTO {
	private int tourId;
	private String tourName;
	private Long tourCost;
	private String tourDescription;
	private String startLoc;
	private int sale;
	private String region;
	private int days;
	private List<TourDayStartDTO> listDayStarts;
	private List<TourImageDTO> listimgs;
	private List<ActivityDTO> listacts;
	private List<BookTourDTO> listbooks;
	private int cateId;
}

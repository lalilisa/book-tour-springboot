package com.ttcs.domain.compositeid;

import java.io.Serializable;
import java.util.Date;

import com.ttcs.domain.Tour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourDayStartId implements Serializable{
	private Tour tour;
	private Date dayStart;
}

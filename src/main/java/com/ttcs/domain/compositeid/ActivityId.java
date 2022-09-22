package com.ttcs.domain.compositeid;

import java.io.Serializable;

import com.ttcs.domain.Tour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityId implements Serializable{
	private Tour tour;
	private int day;
}

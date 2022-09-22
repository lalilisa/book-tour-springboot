package com.ttcs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityDTO {
	private int tourId;
	private int day;
	private String title;
	private String act;

}

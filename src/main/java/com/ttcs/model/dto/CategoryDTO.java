package com.ttcs.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
	private int cateId;
	private String cateName;
	private List<TourDTO> tours;
}

package com.ttcs.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ttcs.domain.Category;
import com.ttcs.domain.Tour;
import com.ttcs.model.dto.CategoryDTO;
import com.ttcs.model.dto.TourDTO;

public class CategoryMapper {
	public static CategoryDTO toCategoryDTO(Category category) {
		CategoryDTO tmp = new CategoryDTO();
		List<TourDTO> list = new ArrayList<>();
		for(Tour b : category.getTours()) {
			list.add(TourMapper.totourDTO(b));
		}
		tmp.setCateId(category.getCateId());
		tmp.setCateName(category.getCateName());
		tmp.setTours(list);
		return tmp;
	}
	
	public static List<CategoryDTO> toListCategoryDTO(List<Category> categories){
		List<CategoryDTO> list = new ArrayList<>();
		for (Category i : categories) {
			list.add(toCategoryDTO(i));
		}
		return list;
	}
}

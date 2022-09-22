package com.ttcs.service;

import java.util.List;

import com.ttcs.domain.Category;

public interface CategoryService {

	Category getById(Integer id);

	boolean existsById(Integer id);

	List<Category> findAll();
	
}

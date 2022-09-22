package com.ttcs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttcs.domain.Category;
import com.ttcs.repository.CategoryRepository;
import com.ttcs.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public boolean existsById(Integer id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public Category getById(Integer id) {
		return categoryRepository.getById(id);
	}
	
	
}

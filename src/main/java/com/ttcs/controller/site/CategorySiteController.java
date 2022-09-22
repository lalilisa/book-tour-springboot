package com.ttcs.controller.site;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttcs.model.dto.CategoryDTO;
import com.ttcs.model.mapper.CategoryMapper;
import com.ttcs.service.CategoryService;
@Tag(name = "Category Controller")
@RestController
@RequestMapping("/data")
public class CategorySiteController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value="/category", produces = "application/json")
	public List<CategoryDTO> getAllCategoryDTO(){
		return CategoryMapper.toListCategoryDTO(categoryService.findAll());
	}
	
	@GetMapping(value="/categoryid", produces = "application/json")
	public CategoryDTO getCategoryDTO(@RequestParam("id") Integer id) {
		return CategoryMapper.toCategoryDTO(categoryService.getById(id));
	}
}

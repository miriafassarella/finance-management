package com.mabrasoft.financemanagement.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mabrasoft.financemanagement.domain.model.Category;
import com.mabrasoft.financemanagement.domain.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<Category> categoryList(){
		return categoryService.list();
	}

}

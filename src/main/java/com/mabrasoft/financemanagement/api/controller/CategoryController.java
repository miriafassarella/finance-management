package com.mabrasoft.financemanagement.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mabrasoft.financemanagement.domain.exceptions.EntityNotFoundException;
import com.mabrasoft.financemanagement.domain.model.Category;
import com.mabrasoft.financemanagement.domain.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Category> categoryList(){
		return categoryService.list();
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> categorySearch(@PathVariable Long categoryId){
		try {
			Category category = categoryService.search(categoryId);
			return ResponseEntity.status(HttpStatus.FOUND).body(category);
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Category> categoryAdd(@RequestBody Category category){
		category = categoryService.add(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> categoryRemove(@PathVariable Long categoryId){
		try {
			categoryService.remove(categoryId);
			return ResponseEntity.noContent().build();
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
}

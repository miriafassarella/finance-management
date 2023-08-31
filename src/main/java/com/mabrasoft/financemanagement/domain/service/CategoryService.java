package com.mabrasoft.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabrasoft.financemanagement.domain.exceptions.EntityNotFoundException;
import com.mabrasoft.financemanagement.domain.model.Category;
import com.mabrasoft.financemanagement.domain.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> list(){
		return categoryRepository.findAll();
	}
	
	public Category search(Long categoryId) {
		Optional<Category> category =  categoryRepository.findById(categoryId);
		
		if(category.isPresent()) {
			return category.get();
		}
			throw new EntityNotFoundException(String.format("Code entity %d not found!", categoryId));
		}
	
	public Category add(Category category) {
		return categoryRepository.save(category);
	}
}

package com.mabrasoft.financemanagement.domain.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabrasoft.financemanagement.domain.model.Category;
import com.mabrasoft.financemanagement.domain.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> list(){
		return categoryRepository.findAll();
	}
	
	public Optional<Category> byName(String name){
		return categoryRepository.findByname(name);
	}
	
	public boolean nameExists(String name) {
		return categoryRepository.existsByName(name);
	}
	
	public Category search(Long categoryId) {
		Optional<Category> category =  categoryRepository.findById(categoryId);
		
			return category.get();
	}
	
	public Category add(Category category) {
		return categoryRepository.save(category);
	}
	
	public void remove(Long categoryId) {
		
		Optional<Category> category = categoryRepository.findById(categoryId);
		categoryRepository.delete(category.get());
	}
	
	public Category update(Long categoryId, Category category) {
	  Optional<Category> currentCategory = categoryRepository.findById(categoryId);
	  
	  if(currentCategory.isEmpty()) 
	  { 
		  throw new NoSuchElementException(); 
	  }
	  BeanUtils.copyProperties(category, currentCategory.get(), "id"); 
	  return categoryRepository.save(currentCategory.get());
	  }
	 
}

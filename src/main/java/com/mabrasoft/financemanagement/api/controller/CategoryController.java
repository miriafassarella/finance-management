package com.mabrasoft.financemanagement.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mabrasoft.financemanagement.domain.model.Category;
import com.mabrasoft.financemanagement.domain.service.CategoryService;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


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
	
			Category category = categoryService.search(categoryId);
			return ResponseEntity.status(HttpStatus.FOUND).body(category);
	}
	
	@PostMapping
	public ResponseEntity<Category> categoryAdd(@Valid @RequestBody Category category, HttpServletResponse response){
		category = categoryService.add(category);
		
		URI  uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id")
		.buildAndExpand(category.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(category);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> categoryRemove(@PathVariable Long categoryId){
	
			categoryService.remove(categoryId);
			return ResponseEntity.noContent().build();
		}

}

package com.mabrasoft.financemanagement.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Category;


public interface CategoryRepository  extends JpaRepository<Category, Long>{

	/*searching just one category by name*/
	Optional<Category> findByname(String name);
	
	/*checking if a category exists*/
	boolean existsByName(String name);
}

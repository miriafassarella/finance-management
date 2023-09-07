package com.mabrasoft.financemanagement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Category;


public interface CategoryRepository  extends JpaRepository<Category, Long>{

}

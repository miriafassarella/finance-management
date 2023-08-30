package com.mabrasoft.financemanagement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mabrasoft.financemanagement.domain.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{

}

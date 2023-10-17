
package com.mabrasoft.financemanagement.domain.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Entry;


public interface EntryRepository extends JpaRepository<Entry, Long>, QueriesEntryRepository{

	List<Entry> findByprice(BigDecimal price);
	
	
	List<Entry> findByDescriptionContaining(String description);
	
	/*Query between one value and another value*/
	List<Entry> findByPriceBetween(BigDecimal priceInitial, BigDecimal priceFinal);
	
	int countBycategoryId(Long category);
	
	
}

package com.mabrasoft.financemanagement.domain.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Entry;


public interface EntryRepository extends JpaRepository<Entry, Long>{

	List<Entry> findByprice(BigDecimal price);
	
	/*Query between one value and another value*/
	List<Entry> findBypriceBetween(BigDecimal priceInitial, BigDecimal priceFinal);
	
	int countBycategory_id(Long category);
}

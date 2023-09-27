
package com.mabrasoft.financemanagement.domain.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Entry;


public interface EntryRepository extends JpaRepository<Entry, Long>{

	List<Entry> price(BigDecimal price);
}

package com.mabrasoft.financemanagement.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mabrasoft.financemanagement.domain.model.Entry;
import com.mabrasoft.financemanagement.domain.repository.QueriesEntryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class EntryRepositoryImpl implements QueriesEntryRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Entry> find(String description,
			BigDecimal priceInitialEntry, BigDecimal priceFinalEntry){
		
		
		
		var jpql = new StringBuilder();
		jpql.append("from Entry where 0 = 0 ");
		
		var parameters = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(description)) { /*Checking if it is not empty or if it is not null*/
			jpql.append("and description like :description ");
			parameters.put("description", "%" + description + "%");
		}
		if(priceInitialEntry != null) {
			jpql.append("and price >= :priceInitial ");
			parameters.put("priceInitial", priceInitialEntry);
		}
		if(priceFinalEntry != null) {
			jpql.append("and price <= :priceFinal ");
			parameters.put("priceFinal", priceFinalEntry);
		}
		
		TypedQuery<Entry> query =  manager.createQuery(jpql.toString(), Entry.class); 
		
		parameters.forEach((key, value) -> query.setParameter(key, value));
				
		return query.getResultList();
	}
}

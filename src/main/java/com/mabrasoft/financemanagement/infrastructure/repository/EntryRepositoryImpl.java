package com.mabrasoft.financemanagement.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mabrasoft.financemanagement.domain.model.Entry;
import com.mabrasoft.financemanagement.domain.repository.QueriesEntryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EntryRepositoryImpl implements QueriesEntryRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Entry> find(String description,
			BigDecimal priceInitial, BigDecimal priceFinal){
		
		var jpql = "from Entry where description like :description "
		+ "and price between :priceInitial and :priceFinal";
		
		return manager.createQuery(jpql, Entry.class)
				.setParameter("description", "%" + description + "%")
				.setParameter("priceInitial", priceInitial)
				.setParameter("priceFinal", priceFinal)
				.getResultList();
	};
}

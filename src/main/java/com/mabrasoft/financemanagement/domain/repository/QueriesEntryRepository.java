package com.mabrasoft.financemanagement.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.mabrasoft.financemanagement.domain.model.Entry;

public interface QueriesEntryRepository {

	public List<Entry> find(String description,BigDecimal priceInitial, BigDecimal priceFinal);
}

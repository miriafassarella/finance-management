package com.mabrasoft.financemanagement.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabrasoft.financemanagement.domain.model.Entry;
import com.mabrasoft.financemanagement.domain.repository.EntryRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;

	public List<Entry> list(){
		return entryRepository.findAll();
	}
}

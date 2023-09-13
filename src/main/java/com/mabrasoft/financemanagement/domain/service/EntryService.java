
package com.mabrasoft.financemanagement.domain.service;

import java.util.List;
import java.util.Optional;

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
	
	public Entry search(Long entryId) {
		Optional<Entry> entry = entryRepository.findById(entryId);
		return entry.get();
	}
	
	public Entry add(Entry entry) {
		return entryRepository.save(entry);
	}
	
	public void remove(Long entryId) {
		Optional<Entry> entry = entryRepository.findById(entryId);
		entryRepository.delete(entry.get());
	}
}

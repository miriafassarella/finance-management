
package com.mabrasoft.financemanagement.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabrasoft.financemanagement.domain.model.Entry;
import com.mabrasoft.financemanagement.domain.model.Person;
import com.mabrasoft.financemanagement.domain.repository.EntryRepository;
import com.mabrasoft.financemanagement.domain.repository.PersonRepository;
import com.mabrasoft.financemanagement.domain.service.exception.PersonNotExistException;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private PersonRepository personRepository;

	public List<Entry> list(){
		return entryRepository.findAll();
	}
	public List<Entry> byPrice(BigDecimal price){
		return entryRepository.findByprice(price);
	}
	
	public Entry search(Long entryId) {
		Optional<Entry> entry = entryRepository.findById(entryId);
		return entry.get();
	}
	
	public Entry add(Entry entry) {
		Optional<Person> person = personRepository.findById(entry.getPerson().getId());
		
		if(person.get().isInactive()) {
			throw new PersonNotExistException(String.format("Person code %d is not active!", person.get().getId()));
		}
		
		return entryRepository.save(entry);
		
	}
	
	public void remove(Long entryId) {
		Optional<Entry> entry = entryRepository.findById(entryId);
		entryRepository.delete(entry.get());
	}
	
	public Entry update(Long entreId, Entry entry) {
		Optional<Entry> currentEntry = entryRepository.findById(entreId);
		
		if(currentEntry.isEmpty()) {
			throw new NoSuchElementException();
		}
		BeanUtils.copyProperties(entry, currentEntry.get(), "id");
		return entryRepository.save(currentEntry.get());
	}
}

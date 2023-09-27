
package com.mabrasoft.financemanagement.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mabrasoft.financemanagement.domain.model.Entry;
import com.mabrasoft.financemanagement.domain.service.EntryService;
import com.mabrasoft.financemanagement.domain.service.exception.PersonNotExistException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/entries")
public class EntryController {
	
	@Autowired
	EntryService entryService;
	
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Entry> entryList(){
		return entryService.list();
	}
	
	@GetMapping("/{entryId}")
	public ResponseEntity<Entry> entrySearch(@PathVariable Long entryId){
		Entry entry = entryService.search(entryId);
		return ResponseEntity.status(HttpStatus.FOUND).body(entry);
	}
	
	@GetMapping("/by-price")
	public List<Entry> byPrice(BigDecimal price){
		return entryService.byPrice(price);
	}
	
	@PostMapping
	public ResponseEntity<?> entryAdd(@Valid @RequestBody Entry entry){
		try {
		Entry savedEntry = entryService.add(entry);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
		}catch(PersonNotExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{entryId}")
	public ResponseEntity<Entry> entryRemove(@PathVariable Long entryId){
		entryService.remove(entryId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	@PutMapping("/{entryId}")
	public ResponseEntity<Entry> entryUpdate(@PathVariable Long entryId, @RequestBody Entry entry){
		Entry entrySave = entryService.update(entryId, entry);
		return ResponseEntity.status(HttpStatus.OK).body(entrySave);
	}
 }

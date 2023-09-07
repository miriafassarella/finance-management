package com.mabrasoft.financemanagement.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mabrasoft.financemanagement.domain.model.Entry;
import com.mabrasoft.financemanagement.domain.service.EntryService;

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
}

package com.mabrasoft.financemanagement.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.mabrasoft.financemanagement.domain.model.Person;

import com.mabrasoft.financemanagement.domain.service.PersonService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public List<Person> personList(){
		return personService.list();
	}
	
	@GetMapping("/by-name")
	public List<Person> byName(String name){
		return personService.byName(name);
	}
	
	@GetMapping("/name-active")
	public List<Person> byNameAndActive(String name, Boolean active){
		return personService.byNameAndActive(name, active);
	}
	
	@GetMapping("/name-2")
	public List<Person> byName2(String name){
		return personService.byName2(name);
	}
	
	@GetMapping("/name-first")
	public Optional<Person> byNameFirst(String name){
		return personService.byNameFirst(name);
	}
	
	
	@GetMapping("/{personId}")
	public ResponseEntity<Person> personSearch(@PathVariable Long personId){
		Person person = personService.search(personId);
		return ResponseEntity.ok(person);
	}
	
	@PostMapping
	public ResponseEntity<Person> personAdd(@RequestBody Person person){
		personService.add(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@DeleteMapping("/{personId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void personRemove (@PathVariable Long personId) {
		personService.remove(personId);
		}
	
	@PutMapping("/{personId}")
	public ResponseEntity<Person> personUpdate(@PathVariable Long personId, @Valid @RequestBody Person person){
		Person personSave = personService.update(personId, person);
		return ResponseEntity.status(HttpStatus.OK).body(personSave);
	}
}

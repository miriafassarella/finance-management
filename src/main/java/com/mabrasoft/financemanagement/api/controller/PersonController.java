package com.mabrasoft.financemanagement.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mabrasoft.financemanagement.domain.model.Person;
import com.mabrasoft.financemanagement.domain.service.PersonService;


@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public List<Person> personList(){
		return personService.list();
	}
	@GetMapping("/{personId}")
	public ResponseEntity<Person> personSearch(@PathVariable Long personId){
		Person person = personService.search(personId);
		return ResponseEntity.ok(person);
	}

}
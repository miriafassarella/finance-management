package com.mabrasoft.financemanagement.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabrasoft.financemanagement.domain.model.Person;
import com.mabrasoft.financemanagement.domain.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> list(){
		return personRepository.findAll();
	
	}
}

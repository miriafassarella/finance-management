package com.mabrasoft.financemanagement.domain.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	public List<Person> byName(String name){
		return personRepository.findBynameContaining(name);
	}
	
	public List<Person> byNameAndActive(String name, Boolean active){
		return personRepository.findBynameContainingAndActive(name, active);
	}
	
	public List<Person> byName2(String name){
		return personRepository.findTop2BynameContaining(name);
	}
	
	public Optional<Person> byNameFirst(String name){
		return personRepository.findFirstBynameContaining(name);
	}
	public Person search(Long personId) {
		Optional<Person> person = personRepository.findById(personId);
		return person.get();
	}
	public Person add(Person person) {
		return personRepository.save(person);
	}
	public void remove(Long personId) {
		Optional<Person> person = personRepository.findById(personId);
		personRepository.delete(person.get());
	}
	
	
	public Person update(Long personId, Person person) {
		Optional<Person> currentPerson = personRepository.findById(personId);
		if(currentPerson.isEmpty()) {
			throw new NoSuchElementException();
		}
		BeanUtils.copyProperties(person, currentPerson.get(), "id");;
		return personRepository.save(currentPerson.get());
		
	}
}

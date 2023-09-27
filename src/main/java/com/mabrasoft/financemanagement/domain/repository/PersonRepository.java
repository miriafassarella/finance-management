package com.mabrasoft.financemanagement.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {

	/*Query by incomplete name*/
	List<Person> findBynameContaining(String name);
	
	/*querying only the first result*/
	Optional<Person> findFirstBynameContaining(String name);
	
	/*query with two conditions*/
	List<Person> findBynameContainingAndActive(String name, Boolean active);
	
	/*consulting only the first two*/
	List<Person> findTop2BynameContaining(String name);
}

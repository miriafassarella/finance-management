package com.mabrasoft.financemanagement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {

}

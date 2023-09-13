
package com.mabrasoft.financemanagement.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mabrasoft.financemanagement.domain.model.Entry;


public interface EntryRepository extends JpaRepository<Entry, Long>{

}

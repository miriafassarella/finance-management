package com.mabrasoft.financemanagement.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Entry {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@Column(name = "due_date")
	private LocalDate DueDate;
	
	@Column(name = "pay_day")
	private LocalDate payDay;
	
	private BigDecimal price;
	
	private String observation;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;

}

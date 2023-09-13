
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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entry {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String description;
	
	@NotNull
	@Column(name = "due_date")
	private LocalDate DueDate;
	
	@NotNull
	@Column(name = "pay_day")
	private LocalDate payDay;
	
	@NotNull
	private BigDecimal price;
	
	private String observation;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	

}

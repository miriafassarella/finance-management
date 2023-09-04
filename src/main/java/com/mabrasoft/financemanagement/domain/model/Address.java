package com.mabrasoft.financemanagement.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class Address {

	private String number;
	
	private String complement;
	
	private String district;
	
	private String zipcode;
	
	private String city;
	
	private String state;
	
	private Boolean active;
}

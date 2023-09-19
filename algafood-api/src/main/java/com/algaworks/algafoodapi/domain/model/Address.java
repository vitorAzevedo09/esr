package com.algaworks.algafoodapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 */
@Embeddable
public class Address {

  @Column(name = "address_postal_code")
  private String postal_code;

  @Column(name = "address_street")
  private String street;

  @Column(name = "address_number")
  private String number;

  @Column(name = "address_complement")
  private String complement;

  @Column(name = "address_neighborhood")
  private String neighborhood;

  @ManyToOne
  @JoinColumn(name = "address_city_id")
  private City city;

public String getPostal_code() {
	return postal_code;
}

public void setPostal_code(String postal_code) {
	this.postal_code = postal_code;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public String getComplement() {
	return complement;
}

public void setComplement(String complement) {
	this.complement = complement;
}

public String getNeighborhood() {
	return neighborhood;
}

public void setNeighborhood(String neighborhood) {
	this.neighborhood = neighborhood;
}

public City getCity() {
	return city;
}

public void setCity(City city) {
	this.city = city;
}



}

package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.CityInput;
import com.algaworks.algafoodapi.api.dto.CityOutput;
import com.algaworks.algafoodapi.domain.model.City;

/**
 * CityAssembler
 */
@Component
public class CityAssembler{

  public CityOutput toOutput(City city) {
    return new CityOutput(city.getId(), city.getName());
  }

  public City toEntity(CityInput cityInput) {
    City city = new City();
    city.setName(cityInput.name());
    return city;
  }

}

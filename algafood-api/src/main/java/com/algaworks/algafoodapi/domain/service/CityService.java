package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.CityNotFoundException;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.repository.CityRepository;

/**
 * CityService
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Page<City> findAll(Pageable page) {
        return cityRepository.findAll(page);
    }

    public City findOrFail(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
    public City create(City inputCity) {
        City citySaved = cityRepository.save(inputCity);
        return citySaved;
    }

    @Transactional
    public City update(final Long id, City inputCity) {
        if (cityRepository.existsById(id)) {
            inputCity.setId(id);
            return cityRepository.save(inputCity);
        }
        throw new CityNotFoundException(id);
    }

    @Transactional
    public void delete(final Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
        }
        throw new CityNotFoundException(id);
    }

}

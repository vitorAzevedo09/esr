package com.algaworks.algafoodapi.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Estado
 */
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Valid
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "estado")
    private List<City> cities = new ArrayList<City>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}

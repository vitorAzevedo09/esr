package com.algaworks.algafoodapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Restaurante
 */
@Entity
@Getter
@Setter
public class Restaurante {

    @Id
    @EqualsAndHashCode
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal taxaFrete;

}

package com.algaworks.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteSpecs
 */
public class RestauranteSpecs {

  public static Specification<Restaurante> comFreteGratis() {
    return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
  }

  public static Specification<Restaurante> comNomeSemelhante(String nome) {
    return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
  }

}

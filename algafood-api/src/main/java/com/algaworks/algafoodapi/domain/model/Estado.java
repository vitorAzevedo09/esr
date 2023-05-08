package com.algaworks.algafoodapi.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Estado
 */
@Entity
public class Estado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @OneToMany(mappedBy = "id")
  private List<Cidade> cidades = new ArrayList<Cidade>();

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public List<Cidade> getCidades() {
    return cidades;
  }

  public void setCidades(List<Cidade> cidades) {
    this.cidades = cidades;
  }

}

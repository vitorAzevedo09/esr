package com.algaworks.algafoodapi.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Grupo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @ManyToMany
  @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
  private List<Permissao> permissoes = new ArrayList<>();

  public Grupo() {
    super();
  }

  public Grupo(Long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPermissoes(List<Permissao> permissoes) {
    this.permissoes = permissoes;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public List<Permissao> getPermissoes() {
    return permissoes;
  }

}

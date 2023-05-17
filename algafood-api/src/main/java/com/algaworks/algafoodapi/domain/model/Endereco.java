package com.algaworks.algafoodapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco {

  @Column(name = "endereco_cep")
  private String cep;

  @Column(name = "endereco_logradouro")
  private String logradouro;

  @Column(name = "endereco_numero")
  private String numero;

  @Column(name = "endereco_complemento")
  private String complemento;

  @Column(name = "endereco_bairro")
  private String bairro;

  @ManyToOne
  @JoinColumn(name = "endereco_cidade_id")
  private Cidade cidade;

  public String getCep() {
    return cep;
  }

  public String getNumero() {
    return numero;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public String getComplemento() {
    return complemento;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public String getBairro() {
    return bairro;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

}

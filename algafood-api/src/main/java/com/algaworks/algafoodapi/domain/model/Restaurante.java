package com.algaworks.algafoodapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Restaurante
 */
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Valid
    @NotNull
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column
    private boolean ativo = Boolean.TRUE;

    @Embedded
    private Endereco endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cozinha_id", referencedColumnName = "id")
    private Cozinha cozinha;

    public void active() {
        this.ativo = true;
    }

    public void deactive() {
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

}

package com.algaworks.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

/**
 * Problema
 */
public record Problema(LocalDateTime dataHora, String mensagem) {

  public static final class Builder {
    LocalDateTime dataHora;
    String mensagem;

    public Builder() {
    }

    public Builder dataHora(LocalDateTime dataHora) {
      this.dataHora = dataHora;
      return this;
    }

    public Builder mensagem(String mensagem) {
      this.mensagem = mensagem;
      return this;
    }

    public Problema build() {
      return new Problema(dataHora, mensagem);
    }
  }
}

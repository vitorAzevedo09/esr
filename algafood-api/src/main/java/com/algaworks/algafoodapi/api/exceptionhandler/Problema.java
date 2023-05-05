package com.algaworks.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

/**
 * Problema
 */
public record Problema(LocalDateTime dataHora, String messagem) {
}

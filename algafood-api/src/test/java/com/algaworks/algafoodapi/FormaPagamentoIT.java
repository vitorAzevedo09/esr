package com.algaworks.algafoodapi;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.util.DatabaseCleaner;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * FormaPagamentoIT
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class FormaPagamentoIT {

  @LocalServerPort
  private int serverPort;

  @Autowired
  private DatabaseCleaner databaseCleaner;

  private void prepararDados() {
    FormaPagamento formaPagamento1 = new FormaPagamento();
    formaPagamento1.setDescricao("TESTE 1");

    FormaPagamento formaPagamento2 = new FormaPagamento();
    formaPagamento2.setDescricao("TESTE 2");
  }

  @BeforeEach
  public void setUp() {
    enableLoggingOfRequestAndResponseIfValidationFails();
    port = serverPort;
    basePath = "/cozinhas";

    databaseCleaner.clearTables();
    prepararDados();
  }

  @Test
  public void deveRetornarStatus200_QuandoConsultarFormasPagamento() {

    given()
        .accept(ContentType.JSON)
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test
  public void deveConter2Cozinhas_QuandoConsultarCozinhas() {

    given()
        .accept(ContentType.JSON)
        .when()
        .get()
        .then()
        .body("content", hasSize(2));
  }

}

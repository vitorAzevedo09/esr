package com.algaworks.algafoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaIT {

  @LocalServerPort
  private int port;

  @Autowired
  private DatabaseCleaner databaseCleaner;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @BeforeEach
  public void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;
    RestAssured.basePath = "/cozinhas";

    databaseCleaner.clearTables();
    prepararDados();
  }

  private void prepararDados() {
    Cozinha cozinha1 = new Cozinha();
    cozinha1.setNome("Tailandesa");
    cozinhaRepository.save(cozinha1);

    Cozinha cozinha2 = new Cozinha();
    cozinha2.setNome("Americana");
    cozinhaRepository.save(cozinha2);

  }

  @Test
  public void deveRetornarStatus200_QuandoConsultarCozinhas() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    given()
        .accept(ContentType.JSON)
        .when()
        .get()
        .then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    given()
        .accept(ContentType.JSON)
        .when()
        .get()
        .then()
        .body("content", hasSize(2));
  }

}

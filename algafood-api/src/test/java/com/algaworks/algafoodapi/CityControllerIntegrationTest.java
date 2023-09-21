package com.algaworks.algafoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafoodapi.api.controller.CityController;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import com.algaworks.algafoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { CityController.class })
@TestPropertySource("/application-test.properties")
public class CityControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CityRepository cityRepository;

    private String jsonCorretoCity;
    private int quantidadeCitiesCadastrados;
    private Long cityIdExistente;
    private Long cityIdInexistente;

    @BeforeAll
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cidades";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCities() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarQuantidadeCorretaDeCities_QuandoConsultarCities() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(quantidadeCitiesCadastrados));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCity() {
        given()
                .body(jsonCorretoCity)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCityExistente() {
        given()
                .pathParam("cityId", cityIdExistente)
                .accept(ContentType.JSON)
                .when()
                .get("/{cityId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo("Nome da Cidade")); // Replace with the expected city name
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCityInexistente() {
        given()
                .pathParam("cityId", cityIdInexistente)
                .accept(ContentType.JSON)
                .when()
                .get("/{cityId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        City city = new City();
        city.setName("Nome da Cidade"); // Replace with the desired city name
        cityRepository.save(city);

        quantidadeCitiesCadastrados = (int) cityRepository.count();
        cityIdExistente = city.getId();
        cityIdInexistente = 100L; // Use an ID that does not exist in your database
    }
}

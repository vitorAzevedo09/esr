package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.service.CityService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class CityControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CityService cityService;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnStatus200WhenFetchingCities() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/cidades")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnCorrectNumberOfCitiesWhenFetchingCities() {
        Pageable pageable = Pageable.ofSize(10);
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/cidades")
        .then()
            .body("size()", equalTo(cityService.findAll(pageable).getSize()));
    }

    @Test
    public void shouldReturnStatus201WhenCreatingCity() {
        City newCity = new City();
        newCity.setName("New City");

        given()
            .contentType(ContentType.JSON)
            .body(newCity)
        .when()
            .post("/cidades")
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    // Add more test cases for other endpoints and scenarios

}

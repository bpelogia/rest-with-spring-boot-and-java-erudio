package br.com.erudio.integrationtest.controller;

import br.com.erudio.configs.TestConfigs;
import br.com.erudio.data.enumeration.Gender;
import br.com.erudio.integrationtest.testcontainer.AbstractIntegrationTest;
import br.com.erudio.integrationtest.vo.PersonVO;
import br.com.erudio.integrationtest.vo.security.AccountCredentialsVO;
import br.com.erudio.integrationtest.vo.security.TokenVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonVO person;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonVO();
    }

    @Test
    @Order(0)
    void authorization() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("bpelogia", "admin1234");

        var accessToken = given()
                .basePath("/auth/signin")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                    .when()
                .post()
                    .then()
                        .statusCode(200)
                            .extract()
                            .body()
                                .as(TokenVO.class)
                            .getAccessToken();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer "+accessToken)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }
    @Test
    @Order(1)
    void testCreate() throws IOException {
        mockPerson();
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ALLOWED)
                        .body(person)
                    .when()
                        .post()
                    .then()
                        .statusCode(200)
                        .extract().body().asString();
        PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
        person = persistedPerson;

        assertFalse(persistedPerson.getId().isEmpty());
        assertNotNull(persistedPerson.getFirstName());
        assertNotNull(persistedPerson.getLastName());
        assertNotNull(persistedPerson.getGender());
        assertNotNull(persistedPerson.getAddress());

        assertEquals("Bruno", persistedPerson.getFirstName());
        assertEquals("Stallman", persistedPerson.getLastName());
        assertEquals("New Youk City - NY", persistedPerson.getAddress());
        assertEquals("MALE", persistedPerson.getGender().toString());

    }

    @Test
    @Order(2)
    void testCreateWithWrongOrigin() throws IOException {
        mockPerson();
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_NOT_ALLOWED)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(403)
                .extract().body().asString();

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    void testFindById() throws IOException {
        mockPerson();
        var content = given().spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ALLOWED)
                        .pathParam("id", person.getId())
                    .when()
                        .get("{id}")
                    .then()
                        .statusCode(200)
                    .extract().body().asString();
        PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
        person = persistedPerson;

        assertFalse(persistedPerson.getId().isEmpty());
        assertNotNull(persistedPerson.getFirstName());
        assertNotNull(persistedPerson.getLastName());
        assertNotNull(persistedPerson.getGender());
        assertNotNull(persistedPerson.getAddress());

        assertEquals("Bruno", persistedPerson.getFirstName());
        assertEquals("Stallman", persistedPerson.getLastName());
        assertEquals("New Youk City - NY", persistedPerson.getAddress());
        assertEquals("MALE", persistedPerson.getGender().toString());

    }
    @Test
    @Order(4)
    void testDelete() throws IOException {
        mockPerson();
        var content = given().spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ALLOWED)
                        .pathParam("id", person.getId())
                    .when()
                        .delete("{id}")
                    .then()
                        .statusCode(204)
                    .extract().body().asString();

        assertTrue(content.isEmpty());
    }

    private void mockPerson() {
        person.setFirstName("Bruno");
        person.setLastName("Stallman");
        person.setAddress("New Youk City - NY");
        person.setGender(Gender.MALE);
    }

}

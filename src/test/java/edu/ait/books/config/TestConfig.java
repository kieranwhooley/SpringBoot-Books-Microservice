package edu.ait.books.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.event.annotation.BeforeTestClass;

public class TestConfig {

    @BeforeAll
    public static void setup() {
        //Setting the base URL and port number for the Rest-Assured tests
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        //Setting the Header contents
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.requestSpecification = requestSpecification;

    }
}

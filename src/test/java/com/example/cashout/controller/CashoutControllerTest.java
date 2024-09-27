package com.example.cashout.controller;

import com.example.cashout.model.User;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CashoutControllerTest {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    WebTestClient webTestClient;

    private void executeScriptBlocking(final Resource sqlScript) {
        Mono.from(connectionFactory.create())
                .flatMap(connection -> ScriptUtils.executeSqlScript(connection, sqlScript))
                .block();
    }

    @BeforeEach
    public void cleanAndSeed(@Value("classpath:seed.sql") Resource script) {
        executeScriptBlocking(script);
    }

    @Test
    void getUserById() {
        webTestClient
                .get()
                .uri("http://localhost:8080/api/v1/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Alice")
                .jsonPath("$.balance").isEqualTo(100900.0);
    }

    @Test
    void createUser() {
        var userRequest = User.builder()
                .name("NOMBRE")
                .balance(9876543.21)
                .build();
        webTestClient
                .post()
                .uri("http://localhost:8080/api/v1/users")
                .bodyValue(userRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("NOMBRE")
                .jsonPath("$.balance").isEqualTo(9876543.21);
    }

    @Test
    void updateUserBalance() {
    }

    @Test
    void createCashout() {
    }

    @Test
    void getCashoutsByUserId() {
    }
}
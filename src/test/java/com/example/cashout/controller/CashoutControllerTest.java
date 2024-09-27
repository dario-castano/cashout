package com.example.cashout.controller;

import com.example.cashout.controller.request.CreateCashoutRequest;
import com.example.cashout.controller.request.UpdateBalanceRequest;
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
        var updateBalanceRequest = new UpdateBalanceRequest(123.45);
        webTestClient.get()
                .uri("http://localhost:8080/api/v1/users/5")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.balance").isEqualTo(500.1);
        webTestClient.put()
                .uri("http://localhost:8080/api/v1/users/5/balance")
                .bodyValue(updateBalanceRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.balance").isEqualTo(123.45);
    }

    @Test
    void createCashout() {
        var createCashoutRequest = new CreateCashoutRequest(1L, 20.20);
        webTestClient.post()
                .uri("http://localhost:8080/api/v1/cashout")
                .bodyValue(createCashoutRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userId").isEqualTo(1)
                .jsonPath("$.amount").isEqualTo(20.20);
    }

    @Test
    void getCashoutsByUserId() {
        webTestClient.get()
                .uri("http://localhost:8080/api/v1/cashouts/user/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].userId").isEqualTo(1)
                .jsonPath("$[0].amount").isEqualTo(100.0)
                .jsonPath("$[1].userId").isEqualTo(1)
                .jsonPath("$[1].amount").isEqualTo(200.34)
                .jsonPath("$[2].userId").isEqualTo(1)
                .jsonPath("$[2].amount").isEqualTo(3.5);
    }
}
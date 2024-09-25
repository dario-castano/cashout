package com.example.cashout.controller;

import com.example.cashout.controller.request.CreateCashoutRequest;
import com.example.cashout.controller.request.UpdateBalanceRequest;
import com.example.cashout.model.Cashout;
import com.example.cashout.model.User;
import com.example.cashout.service.CashoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class CashoutController {

    @Autowired
    private CashoutService cashoutService;

    @GetMapping("/users/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return cashoutService.getUserById(id);
    }

    @PostMapping("/users")
    public Mono<User> createUser(@RequestBody User user) {
        return cashoutService.createUser(user);
    }

    @PutMapping("/users/{id}/balance")
    public Mono<User> updateUserBalance(@PathVariable Long id, @RequestBody UpdateBalanceRequest request) {
        return cashoutService.updateUserBalance(id, request.getAmount());
    }

    @PostMapping("/cashout")
    public Mono<Cashout> createCashout(@RequestBody CreateCashoutRequest request) {
        return cashoutService.createCashout(
                Cashout.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .build());
    }

    @GetMapping("/cashouts/user/{userId}")
    public Flux<Cashout> getCashoutsByUserId(@PathVariable Long userId) {
        return cashoutService.getCashoutsByUserId(userId);
    }
}

package com.example.cashout.controller;

import com.example.cashout.controller.request.UpdateBalanceRequest;
import com.example.cashout.model.User;
import com.example.cashout.service.CashoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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
}

package com.example.cashout.service;

import com.example.cashout.controller.request.CreateCashoutRequest;
import com.example.cashout.model.Cashout;
import com.example.cashout.model.User;
import com.example.cashout.persistence.entity.CashoutEntity;
import com.example.cashout.persistence.entity.UserEntity;
import com.example.cashout.persistence.repository.CashoutRepository;
import com.example.cashout.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashoutServiceTest {
    @Mock
    private CashoutRepository cashoutRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CashoutService cashoutService;

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Mono.just(UserEntity.builder()
                .id(1L)
                .name("Alice")
                .balance(100.0)
                .build()));
        var result = cashoutService.getUserById(1L);
        StepVerifier.create(result)
                .expectNextMatches(user -> {
                    assertEquals(1L, user.getId());
                    assertEquals("Alice", user.getName());
                    assertEquals(100.0, user.getBalance());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void createUser() {
        when(userRepository.save(UserEntity.builder()
                .name("NOMBRE")
                .balance(9876543.21)
                .build())).thenReturn(Mono.just(UserEntity.builder()
                .id(1L)
                .name("NOMBRE")
                .balance(9876543.21)
                .build()));
        var result = cashoutService.createUser(User
                .builder()
                .name("NOMBRE")
                .balance(9876543.21)
                .build());
        StepVerifier.create(result).expectNextMatches(user -> {
            assertEquals(1L, user.getId());
            assertEquals("NOMBRE", user.getName());
            assertEquals(9876543.21, user.getBalance());
            return true;
        }).verifyComplete();
    }

    @Test
    void updateUserBalance() {
        when(userRepository.findById(1L)).thenReturn(Mono.just(UserEntity.builder()
                .id(1L)
                .name("Alice")
                .balance(100.0)
                .build()));
        when(userRepository.save(UserEntity.builder()
                .id(1L)
                .name("Alice")
                .balance(200.0)
                .build())).thenReturn(Mono.just(UserEntity.builder()
                .id(1L)
                .name("Alice")
                .balance(200.0)
                .build()));
        var result = cashoutService.updateUserBalance(1L, 200.0);
        StepVerifier.create(result).expectNextMatches(user -> {
            assertEquals(1L, user.getId());
            assertEquals("Alice", user.getName());
            assertEquals(200.0, user.getBalance());
            return true;
        }).verifyComplete();
    }

    @Test
    void getCashoutsByUserId() {
        var c1 = CashoutEntity.builder()
                .id(1L)
                .userId(1L)
                .amount(20.20)
                .build();
        var c2 = CashoutEntity.builder()
                .id(2L)
                .userId(1L)
                .amount(30.30)
                .build();
        when(cashoutRepository.findAllByUserId(1L))
                .thenReturn(Flux.just(c1, c2));
        var result = cashoutService.getCashoutsByUserId(1L);
        StepVerifier.create(result)
                .expectNextMatches(cashout -> {
                    assertEquals(1L, cashout.getId());
                    assertEquals(1L, cashout.getUserId());
                    assertEquals(20.20, cashout.getAmount());
                    return true;
                })
                .expectNextMatches(cashout -> {
                    assertEquals(2L, cashout.getId());
                    assertEquals(1L, cashout.getUserId());
                    assertEquals(30.30, cashout.getAmount());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void createCashout() {
        when(cashoutRepository.save(CashoutEntity.builder()
                .userId(1L)
                .amount(20.20)
                .build())).thenReturn(Mono.just(CashoutEntity.builder()
                .id(1L)
                .userId(1L)
                .amount(20.20)
                .build()));
        var result = cashoutService.createCashout(Cashout.builder()
                .userId(1L)
                .amount(20.20)
                .build());
        StepVerifier.create(result).expectNextMatches(cashout -> {
            assertEquals(1L, cashout.getId());
            assertEquals(1L, cashout.getUserId());
            assertEquals(20.20, cashout.getAmount());
            return true;
        }).verifyComplete();
    }
}
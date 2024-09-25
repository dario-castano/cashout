package com.example.cashout.service;

import com.example.cashout.model.Cashout;
import com.example.cashout.model.User;
import com.example.cashout.persistence.entity.UserEntity;
import com.example.cashout.persistence.entity.CashoutEntity;
import com.example.cashout.persistence.repository.CashoutRepository;
import com.example.cashout.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CashoutService {
    @Autowired
    private CashoutRepository cashoutRepository;

    @Autowired
    private UserRepository userRepository;

    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> User.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .balance(userEntity.getBalance())
                        .build());
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(UserEntity.builder()
                .name(user.getName())
                .balance(user.getBalance())
                .build())
                .map(userEntity -> User.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .balance(userEntity.getBalance())
                        .build());
    }

    public Mono<User> updateUserBalance(Long id, Double amount) {
        return userRepository.findById(id)
                .map(userEntity -> UserEntity.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .balance(amount)
                        .build())
                .flatMap(userRepository::save)
                .map(userEntity -> User.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .balance(userEntity.getBalance())
                        .build());
    }

    public Flux<Cashout> getCashoutsByUserId(Long userId) {
        return cashoutRepository.findAllByUserId(userId)
                .map(cashoutEntity -> Cashout.builder()
                        .id(cashoutEntity.getId())
                        .userId(cashoutEntity.getUserId())
                        .amount(cashoutEntity.getAmount())
                        .build());
    }

    public Mono<Cashout> createCashout(Cashout cashout) {
        return cashoutRepository.save(CashoutEntity.builder()
                .userId(cashout.getUserId())
                .amount(cashout.getAmount())
                .build())
                .map(cashoutEntity -> Cashout.builder()
                        .id(cashoutEntity.getId())
                        .userId(cashoutEntity.getUserId())
                        .amount(cashoutEntity.getAmount())
                        .build());
    }
}

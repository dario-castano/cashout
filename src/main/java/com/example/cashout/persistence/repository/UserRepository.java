package com.example.cashout.persistence.repository;

import com.example.cashout.persistence.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}

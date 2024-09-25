package com.example.cashout.persistence.repository;

import com.example.cashout.persistence.entity.CashoutEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CashoutRepository extends ReactiveCrudRepository<CashoutEntity, Long> {
    @Query("SELECT * FROM system.cashouts WHERE user_id = :userId")
    Flux<CashoutEntity> findAllByUserId(Long userId);
}

package com.example.cashout.persistence.repository;

import com.example.cashout.persistence.entity.CashoutEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CashoutRepository extends ReactiveCrudRepository<CashoutEntity, Long> {
}

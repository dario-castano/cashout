package com.example.cashout.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Table(name = "cashouts")
public class CashoutEntity {
    @Id
    private Long id;
    private Long userId;
    private BigDecimal amount;
}

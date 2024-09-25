package com.example.cashout.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private BigDecimal balance;
}

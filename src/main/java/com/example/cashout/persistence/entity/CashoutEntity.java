package com.example.cashout.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "cashouts")
public class CashoutEntity {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    private Double amount;
}

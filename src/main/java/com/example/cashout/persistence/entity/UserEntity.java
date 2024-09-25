package com.example.cashout.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private Double balance;
}

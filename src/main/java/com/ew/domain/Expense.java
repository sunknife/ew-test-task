package com.ew.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Expense {
    @EqualsAndHashCode.Exclude private Long id;
    private ExpenseType type = ExpenseType.PAYMENTS;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private Long userId;
}

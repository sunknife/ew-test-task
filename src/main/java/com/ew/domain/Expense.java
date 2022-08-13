package com.ew.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Expense {
    private Long id;
    private ExpenseType type = ExpenseType.PAYMENTS;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private Long userId;
}

package com.ew.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private Long id;
    private ExpenseType type = ExpenseType.PAYMENTS;
    private BigDecimal amount;
    private LocalDate date;
    private Long userId;
}

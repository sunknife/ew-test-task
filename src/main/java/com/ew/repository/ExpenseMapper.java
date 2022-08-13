package com.ew.repository;

import com.ew.domain.Expense;
import com.ew.domain.ExpenseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseMapper {
    public Expense extractFromResultSet(ResultSet resultSet) throws SQLException {
        Expense expense = new Expense();
        expense.setType(ExpenseType.valueOf(resultSet.getString("type")));
        expense.setAmount(resultSet.getBigDecimal("amount"));
        expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());
        expense.setUserId(resultSet.getLong("user_id"));
        return expense;
    }
}

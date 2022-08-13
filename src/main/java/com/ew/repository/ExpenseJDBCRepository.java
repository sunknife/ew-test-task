package com.ew.repository;

import com.ew.domain.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseJDBCRepository {

    private Connection connection;


    public ExpenseJDBCRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Expense entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO expense (type, amount, expense_date, user_id) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getType().toString());
            preparedStatement.setBigDecimal(2, entity.getAmount());
            preparedStatement.setDate(3, Date.valueOf(entity.getExpenseDate()));
            preparedStatement.setLong(4, entity.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> findAll() {
        List<Expense> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM expense")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExpenseMapper expenseMapper = new ExpenseMapper();
                result.add(expenseMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

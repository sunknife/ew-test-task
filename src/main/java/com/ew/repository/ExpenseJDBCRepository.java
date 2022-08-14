package com.ew.repository;

import com.ew.domain.Expense;
import com.ew.domain.ExpenseType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseJDBCRepository {

    private final Connection connection;


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

    public List<Expense> findByCategory(ExpenseType type) {
        List<Expense> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expense WHERE type=?")) {
            preparedStatement.setString(1, type.toString());
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

    public List<Expense> findByYear(int year) {
        List<Expense> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expense WHERE EXTRACT(YEAR from expense_date)=?")) {
            preparedStatement.setInt(1, year);
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

    public List<Expense> findByMonth(int year, int month) {
        List<Expense> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expense WHERE EXTRACT(YEAR from expense_date)=? AND EXTRACT(MONTH FROM expense_date)=?")) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);
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

    public List<Expense> findByDate(LocalDate date) {
        List<Expense> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expense WHERE expense_date=?")) {
            preparedStatement.setDate(1, Date.valueOf(date));
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

    public void deleteAll() {
        try (PreparedStatement preparedStatement = connection.prepareCall("DELETE FROM expense")){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.ew.repository;

import com.ew.domain.Expense;
import com.ew.domain.ExpenseType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseJDBCRepositoryTest extends BaseTest{

    private final ExpenseJDBCRepository repository;

    ExpenseJDBCRepositoryTest() throws SQLException {
        this.repository = new ExpenseJDBCRepository(dataSource.getConnection());
    }

    @AfterEach
    void cleanAll() {
        repository.deleteAll();
    }


    @Test
    void create() {
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now());
        expense.setType(ExpenseType.PAYMENTS);
        expense.setAmount(new BigDecimal("30.00"));
        expense.setUserId(1L);
        repository.create(expense);
        Expense foundExpense = repository.findByDate(LocalDate.now()).get(0);
        assertAll(
                ()->assertEquals(foundExpense.getExpenseDate(), expense.getExpenseDate()),
                ()->assertEquals(foundExpense.getAmount(), expense.getAmount()),
                ()->assertEquals(foundExpense.getType(), expense.getType()),
                ()->assertEquals(foundExpense.getUserId(), expense.getUserId())
        );
    }

    @Test
    void findAll() {
        Expense expenseOne = new Expense();
        expenseOne.setExpenseDate(LocalDate.now());
        expenseOne.setType(ExpenseType.PAYMENTS);
        expenseOne.setAmount(new BigDecimal("30.00"));
        expenseOne.setUserId(1L);
        repository.create(expenseOne);
        Expense expenseTwo = new Expense();
        expenseTwo.setExpenseDate(LocalDate.now());
        expenseTwo.setType(ExpenseType.PAYMENTS);
        expenseTwo.setAmount(new BigDecimal("30.00"));
        expenseTwo.setUserId(1L);
        repository.create(expenseTwo);
        List<Expense> foundExpenses = repository.findAll();
        assertAll(
                ()->assertTrue(foundExpenses.contains(expenseTwo)),
                ()->assertTrue(foundExpenses.contains(expenseOne))
        );
    }

    @Test
    void findByCategory() {
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now());
        expense.setType(ExpenseType.GAMES);
        expense.setAmount(new BigDecimal("220.00"));
        expense.setUserId(1L);
        repository.create(expense);
        Expense foundExpense = repository.findByCategory(ExpenseType.GAMES).get(0);
        assertAll(
                ()->assertEquals(foundExpense.getType(), expense.getType()),
                ()->assertEquals(foundExpense.getExpenseDate(), expense.getExpenseDate()),
                ()->assertEquals(foundExpense.getAmount(), expense.getAmount()),
                ()->assertEquals(foundExpense.getUserId(), expense.getUserId())
        );
    }

    @Test
    void findByYear() {
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now());
        expense.setType(ExpenseType.GAMES);
        expense.setAmount(new BigDecimal("220.00"));
        expense.setUserId(1L);
        repository.create(expense);
        Expense foundExpense = repository.findByYear(expense.getExpenseDate().getYear()).get(0);
        assertAll(
                ()->assertEquals(foundExpense.getType(), expense.getType()),
                ()->assertEquals(foundExpense.getExpenseDate(), expense.getExpenseDate()),
                ()->assertEquals(foundExpense.getAmount(), expense.getAmount()),
                ()->assertEquals(foundExpense.getUserId(), expense.getUserId())
        );
    }

    @Test
    void findByMonth() {
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now());
        expense.setType(ExpenseType.GAMES);
        expense.setAmount(new BigDecimal("220.00"));
        expense.setUserId(1L);
        repository.create(expense);
        Expense foundExpense = repository.findByMonth(expense.getExpenseDate().getYear(), expense.getExpenseDate().getMonthValue()).get(0);
        assertAll(
                ()->assertEquals(foundExpense.getType(), expense.getType()),
                ()->assertEquals(foundExpense.getExpenseDate(), expense.getExpenseDate()),
                ()->assertEquals(foundExpense.getAmount(), expense.getAmount()),
                ()->assertEquals(foundExpense.getUserId(), expense.getUserId())
        );
    }

    @Test
    void findByDate() {
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now());
        expense.setType(ExpenseType.GAMES);
        expense.setAmount(new BigDecimal("220.00"));
        expense.setUserId(1L);
        repository.create(expense);
        Expense foundExpense = repository.findByDate(expense.getExpenseDate()).get(0);
        assertAll(
                ()->assertEquals(foundExpense.getType(), expense.getType()),
                ()->assertEquals(foundExpense.getExpenseDate(), expense.getExpenseDate()),
                ()->assertEquals(foundExpense.getAmount(), expense.getAmount()),
                ()->assertEquals(foundExpense.getUserId(), expense.getUserId())
        );
    }

    @Test
    void deleteAll() {
        Expense expenseOne = new Expense();
        expenseOne.setExpenseDate(LocalDate.now());
        expenseOne.setType(ExpenseType.PAYMENTS);
        expenseOne.setAmount(new BigDecimal("30.00"));
        expenseOne.setUserId(1L);
        repository.create(expenseOne);
        Expense expenseTwo = new Expense();
        expenseTwo.setExpenseDate(LocalDate.now());
        expenseTwo.setType(ExpenseType.PAYMENTS);
        expenseTwo.setAmount(new BigDecimal("30.00"));
        expenseTwo.setUserId(1L);
        repository.create(expenseTwo);
        repository.deleteAll();
        List<Expense> foundExpenses = repository.findAll();
        assertTrue(foundExpenses.isEmpty());
    }
}
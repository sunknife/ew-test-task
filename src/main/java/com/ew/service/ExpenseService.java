package com.ew.service;

import com.ew.domain.Expense;
import com.ew.domain.ExpenseType;
import com.ew.repository.ExpenseJDBCRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ExpenseService {

    private ExpenseJDBCRepository repository;

    public ExpenseService(ExpenseJDBCRepository repository) {
        this.repository = repository;
    }

    public Expense createLatestExpense(Scanner scanner) {
        Expense expense = new Expense();
        System.out.println("Enter expense amount");
        expense.setAmount(scanner.nextBigDecimal());
        expense.setType(selectExpenseType(scanner));
        expense.setExpenseDate(LocalDate.now());
        System.out.println("Enter user id");
        expense.setUserId(scanner.nextLong());

        repository.create(expense);

        return expense;
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    private ExpenseType selectExpenseType(Scanner scanner) {
        boolean inputIncorrect = true;
        ExpenseType result = ExpenseType.PAYMENTS;
        while (inputIncorrect) {
            System.out.println("Select expense type:");
            System.out.println("press 1 - Food");
            System.out.println("press 2 - Entertainment");
            System.out.println("press 3 - Games");
            System.out.println("press 4 - Payments");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    result = ExpenseType.FOOD;
                    inputIncorrect = false;
                    break;
                case "2":
                    result = ExpenseType.ENTERTAINMENT;
                    inputIncorrect = false;
                    break;
                case "3":
                    result = ExpenseType.GAMES;
                    inputIncorrect = false;
                    break;
                case "4":
                    result = ExpenseType.PAYMENTS;
                    inputIncorrect = false;
                default:
                    System.out.println("Please try again!");
                    break;
            }
        }

        return result;
    }
}

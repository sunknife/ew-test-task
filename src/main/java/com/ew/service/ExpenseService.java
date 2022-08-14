package com.ew.service;

import com.ew.domain.Expense;
import com.ew.domain.ExpenseType;
import com.ew.repository.ExpenseJDBCRepository;

import java.math.BigDecimal;
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
        expense.setAmount(new BigDecimal(scanner.nextLine()));
        expense.setType(selectExpenseType(scanner));
        expense.setExpenseDate(LocalDate.now());
        System.out.println("Enter user id");
        expense.setUserId(Long.parseLong(scanner.nextLine()));

        repository.create(expense);

        return expense;
    }

    public Expense createCustomExpense(Scanner scanner) {
        Expense expense = new Expense();
        System.out.println("Enter expense amount");
        expense.setAmount(new BigDecimal(scanner.nextLine()));
        expense.setType(selectExpenseType(scanner));
        expense.setExpenseDate(createDate(scanner));
        System.out.println("Enter user id");
        expense.setUserId(Long.parseLong(scanner.nextLine()));

        repository.create(expense);

        return expense;
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public List<Expense> findByCategory(Scanner scanner) {
        return repository.findByCategory(selectExpenseType(scanner));
    }

    public ExpenseType selectExpenseType(Scanner scanner) {
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
                    break;
                default:
                    System.out.println("Please try again!");
                    break;
            }
        }

        return result;
    }

    public LocalDate createDate(Scanner scanner) {
        System.out.println("Please enter year");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Month (number)");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter day");
        int day = Integer.parseInt(scanner.nextLine());
        return LocalDate.of(year,month,day);
    }

    public void printExpenses(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No data found");
        } else {
            for (Expense expense: expenses) {
                printExpense(expense);
            }
        }
    }

    public void printExpense(Expense expense) {
        System.out.println(expense.getExpenseDate() + " " + expense.getType() + " " + expense.getAmount() + "UAH");
    }

    public List<Expense> findByDate(Scanner scanner) {
        LocalDate date = createDate(scanner);
        return repository.findByDate(date);
    }

    public List<Expense> findByYear(Scanner scanner) {
        System.out.println("Enter Year");
        int year = Integer.parseInt(scanner.nextLine());
        return repository.findByYear(year);
    }

    public List<Expense> findByMonth(Scanner scanner) {
        System.out.println("Enter Year");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Month (numeric)");
        int month = Integer.parseInt(scanner.nextLine());
        return repository.findByMonth(year, month);
    }
}

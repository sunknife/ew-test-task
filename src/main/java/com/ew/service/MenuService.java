package com.ew.service;

import com.ew.repository.ExpenseJDBCRepository;
import com.ew.repository.PersonJDBCRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {

    private final DataSource dataSource;

    public MenuService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void processMenu() throws SQLException {
        PersonService personService = new PersonService(new PersonJDBCRepository(dataSource.getConnection()));
        ExpenseService expenseService = new ExpenseService(new ExpenseJDBCRepository(dataSource.getConnection()));

        String mainInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!mainInput.equals("0")){
            System.out.println("---------------------");
            System.out.println("-----Main menu-------");
            System.out.println("press 1 - Go to User menu");
            System.out.println("press 2 - Go to Expenses menu");
            System.out.println("press 0 - Exit");
            mainInput =scanner.nextLine();
            if (mainInput.equals("1")) {
                String personMenuInput = "";
                while (!personMenuInput.equals("0")) {
                    System.out.println("---------------------");
                    System.out.println("-----Person menu-----");
                    System.out.println("press 1 - Create User");
                    System.out.println("press 2 - Show all users");
                    System.out.println("press 3 - Delete all users");
                    System.out.println("press 0 - Return to Main menu");
                    personMenuInput = scanner.nextLine();
                    if (personMenuInput.equals("1")) {
                        personService.printPerson(personService.create(scanner));
                    }
                    if (personMenuInput.equals("2")) {
                        personService.printPersons(personService.findAll());
                    }
                    if (personMenuInput.equals("3")) {
                        personService.deleteAll();
                    }
                }
            }
            if (mainInput.equals("2")) {
                String expenseMenuInput = "";
                while (!expenseMenuInput.equals("0")) {
                    System.out.println("---------------------");
                    System.out.println("----Expense menu-----");
                    System.out.println("press 1 - Create last Expense");
                    System.out.println("press 2 - Create custom expense");
                    System.out.println("press 3 - Show all expenses");
                    System.out.println("press 4 - Show expenses by category");
                    System.out.println("press 5 - Show expenses by date");
                    System.out.println("press 6 - Clean all expenses");
                    System.out.println("press 0 - Return to Main menu");
                    expenseMenuInput = scanner.nextLine();
                    if (expenseMenuInput.equals("1")) {
                        expenseService.printExpense(expenseService.createLatestExpense(scanner));
                    }
                    if (expenseMenuInput.equals("2")) {
                        expenseService.printExpense(expenseService.createCustomExpense(scanner));
                    }
                    if (expenseMenuInput.equals("3")) {
                        expenseService.printExpenses(expenseService.findAll());
                    }
                    if (expenseMenuInput.equals("4")) {
                        expenseService.printExpenses(expenseService.findByCategory(scanner));
                    }
                    if (expenseMenuInput.equals("5")) {
                        String findByDateInput = "";
                        while (!findByDateInput.equals("0")) {
                            System.out.println("---------------------");
                            System.out.println("---Date statistics---");
                            System.out.println("press 1 - show statistics by year");
                            System.out.println("press 2 - show statistics by month");
                            System.out.println("press 3 - show statistics by day");
                            System.out.println("press 0 - return to Expense menu");
                            findByDateInput = scanner.nextLine();
                            if (findByDateInput.equals("1")) {
                                expenseService.printExpenses(expenseService.findByYear(scanner));
                            }
                            if (findByDateInput.equals("2")) {
                                expenseService.printExpenses(expenseService.findByMonth(scanner));
                            }
                            if (findByDateInput.equals("3")) {
                                expenseService.printExpenses(expenseService.findByDate(scanner));
                            }
                        }
                    }
                    if (expenseMenuInput.equals("6")) {
                        expenseService.deleteAll();
                    }
                }


            }
         }
        System.out.println("---Successful exit---");
    }
}

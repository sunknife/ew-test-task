package com.ew.service;

import com.ew.repository.ExpenseJDBCRepository;
import com.ew.repository.PersonJDBCRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {

    private DataSource dataSource;

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
                    System.out.println("press 0 - Return to Main menu");
                    personMenuInput = scanner.nextLine();
                    if (personMenuInput.equals("1")) {
                        System.out.println(personService.create(scanner));
                    }
                    if (personMenuInput.equals("2")) {
                        System.out.println(personService.findAll());
                    }
                }
            }
            if (mainInput.equals("2")) {
                String expenseMenuInput = "";
                while (!expenseMenuInput.equals(0)) {
                    System.out.println("---------------------");
                    System.out.println("----Expense menu-----");
                    System.out.println("press 1 - Create last Expense");
                    System.out.println("press 2 - Create custom expense");
                    System.out.println("press 3 - Show all expenses");
                    System.out.println("press 0 - Return to Main menu");
                    expenseMenuInput = scanner.nextLine();
                    if (expenseMenuInput.equals("1")) {
                        System.out.println(expenseService.createLatestExpense(scanner));
                    }
                    if (expenseMenuInput.equals("3")) {
                        System.out.println(expenseService.findAll());
                    }
                }


            }
         }
        System.out.println("---Successful exit---");
    }
}

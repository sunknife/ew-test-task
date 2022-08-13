package com.ew.service;

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
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!input.equals("0")){
            System.out.println("---------------------");
            System.out.println("-----Main menu-------");
            System.out.println("press 1 - Go to User menu");
            System.out.println("press 0 - Exit");
            input =scanner.nextLine();
            if (input.equals("1")) {
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
         }
        System.out.println("---Successful exit---");
    }
}

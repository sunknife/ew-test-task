package com.ew.service;

import com.ew.domain.Expense;
import com.ew.domain.ExpenseType;
import com.ew.domain.Person;
import com.ew.repository.ExpenseJDBCRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ExpenseService {

    private final ExpenseJDBCRepository repository;
    private final PersonService personService;

    public ExpenseService(ExpenseJDBCRepository repository, PersonService personService) {
        this.repository = repository;
        this.personService = personService;
    }

    public Expense createLatestExpense(Scanner scanner) {
        Expense expense = new Expense();
        System.out.println("Enter expense amount");
        expense.setAmount(new BigDecimal(scanner.nextLine()));
        expense.setType(selectExpenseType(scanner));
        expense.setExpenseDate(LocalDate.now());
        System.out.println("Choose user id");
        expense.setUserId(chooseId(personService, scanner));

        repository.create(expense);

        return expense;
    }

    private Long chooseId(PersonService personService, Scanner scanner) {
        List<Person> personList = personService.findAll();
        Set<Long> personIds = new HashSet<>();
        for (Person person : personList) {
            personIds.add(person.getId());
        }
        long input = 0L;
        while (!personIds.contains(input)) {
            System.out.println("Available users:");
            printAvailableUsers(personList);
            input = Long.parseLong(scanner.nextLine());
            if (!personIds.contains(input)) {
                System.out.println("No such id. Please try again");
            }
        }
        return input;
    }

    private void printAvailableUsers(List<Person> personList) {
        for (Person person : personList) {
            personService.printFullPerson(person);
        }
    }

    public Expense createCustomExpense(Scanner scanner) {
        Expense expense = new Expense();
        System.out.println("Enter expense amount");
        expense.setAmount(new BigDecimal(scanner.nextLine()));
        expense.setType(selectExpenseType(scanner));
        expense.setExpenseDate(createDate(scanner));
        System.out.println("Choose user id");
        expense.setUserId(chooseId(personService, scanner));

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
        Person person = personService.findById(expense.getUserId());
        System.out.println(expense.getExpenseDate() + " " + expense.getType() + " " + expense.getAmount() + "UAH " + person.getFirstName() + " " + person.getLastName());
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

    public void deleteAll() {
        repository.deleteAll();
    }
}

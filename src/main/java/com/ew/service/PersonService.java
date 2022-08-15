package com.ew.service;

import com.ew.domain.Person;
import com.ew.repository.ExpenseJDBCRepository;
import com.ew.repository.PersonJDBCRepository;

import java.util.List;
import java.util.Scanner;

public class PersonService {

    private final PersonJDBCRepository repository;
    private final ExpenseJDBCRepository expenseJDBCRepository;

    public PersonService(PersonJDBCRepository repository, ExpenseJDBCRepository expenseJDBCRepository) {
        this.repository = repository;
        this.expenseJDBCRepository = expenseJDBCRepository;
    }

    public Person create(Scanner scanner) {
        Person person = new Person();
        System.out.println("Enter first name");
        person.setFirstName(scanner.nextLine());
        System.out.println("Enter last name");
        person.setLastName(scanner.nextLine());
        System.out.println("Enter email");
        person.setEmail(scanner.nextLine());

        repository.create(person);

        return person;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public void deleteAll() {
        repository.deleteAll();
        expenseJDBCRepository.deleteAll();
    }

    public void printPersons(List<Person> personList) {
        if (personList.isEmpty()) {
            System.out.println("No data found");
        } else {
            for (Person person : personList) {
                printPerson(person);
            }
        }
    }

    public void printPerson(Person person) {
        System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getEmail());
    }

    public void printFullPerson(Person person) {
        System.out.println("Press " + person.getId() + " to select " + person.getFirstName() + " " + person.getLastName() + " " + person.getEmail());
    }

    public Person findById(Long userId) {
        return repository.findById(userId);
    }
}

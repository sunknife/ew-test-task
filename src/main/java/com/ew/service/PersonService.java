package com.ew.service;

import com.ew.domain.Person;
import com.ew.repository.PersonJDBCRepository;

import java.util.List;
import java.util.Scanner;

public class PersonService {

    private PersonJDBCRepository repository;

    public PersonService(PersonJDBCRepository repository) {
        this.repository = repository;
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
}

package com.ew.repository;

import com.ew.domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonJDBCRepositoryTest extends BaseTest {

    private final PersonJDBCRepository repository;

    public PersonJDBCRepositoryTest() throws SQLException {
        this.repository = new PersonJDBCRepository(dataSource.getConnection());
    }

    @AfterEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void create() {
        Person person = new Person();
        person.setEmail("example@email.com");
        person.setFirstName("John");
        person.setLastName("Lasseter");
        repository.create(person);
        Person foundPerson = repository.findAll().get(0);
        assertAll(
                ()->assertEquals(person.getEmail(), foundPerson.getEmail()),
                ()->assertEquals(person.getFirstName(), foundPerson.getFirstName()),
                ()->assertEquals(person.getLastName(), foundPerson.getLastName())
        );
    }

    @Test
    void findAll() {
        Person firstPerson = new Person();
        firstPerson.setEmail("example@email.com");
        firstPerson.setFirstName("John");
        firstPerson.setLastName("Lasseter");
        repository.create(firstPerson);
        Person secondPerson = new Person();
        secondPerson.setEmail("test@email.com");
        secondPerson.setFirstName("Walt");
        secondPerson.setLastName("Disney");
        repository.create(secondPerson);
        List<Person> foundPersons = repository.findAll();
        assertAll(
                ()->assertTrue(foundPersons.contains(firstPerson)),
                ()->assertTrue(foundPersons.contains(secondPerson))
        );
    }

    @Test
    void deleteAll() {
        Person firstPerson = new Person();
        firstPerson.setEmail("example@email.com");
        firstPerson.setFirstName("John");
        firstPerson.setLastName("Lasseter");
        repository.create(firstPerson);
        Person secondPerson = new Person();
        secondPerson.setEmail("test@email.com");
        secondPerson.setFirstName("Walt");
        secondPerson.setLastName("Disney");
        repository.create(secondPerson);
        repository.deleteAll();
        List<Person> foundPersons = repository.findAll();
        assertTrue(foundPersons.isEmpty());
    }
}
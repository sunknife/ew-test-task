package com.ew.repository;

import com.ew.domain.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonJDBCRepository {
    private Connection connection;


    public PersonJDBCRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Person entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (first_name, last_name, email) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM person")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            PersonMapper personMapper = new PersonMapper();
            while (resultSet.next()) {
                result.add(personMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

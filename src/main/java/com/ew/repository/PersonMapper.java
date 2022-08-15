package com.ew.repository;

import com.ew.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper {

    public Person extractFromResultSet(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong("id"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}

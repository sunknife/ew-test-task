package com.ew.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Person {
    @EqualsAndHashCode.Exclude private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

package com.restproject.users.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPut {

    private String name;
    private String surname;
    private String address;
    private Date birthdate;
}

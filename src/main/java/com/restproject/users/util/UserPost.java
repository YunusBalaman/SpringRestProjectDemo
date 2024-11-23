package com.restproject.users.util;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPost {

    private long faketckimlikno;
    private String name;
    private String surname;
    private Date birthdate;
    private String address;
}

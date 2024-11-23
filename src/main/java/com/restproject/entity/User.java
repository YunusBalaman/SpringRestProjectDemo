package com.restproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Generated
    private long id;

    //@Generated
    @Column(name = "musteri_no", nullable = false, unique = true)
    //@JsonProperty("musterino")
    private long musterino;

    @Column(name = "faketckimlikno", nullable = false, unique = true)
    private long faketckimlikno;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birthdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name="updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public User(Long faketckimlikno, String name, String surname, Date birthdate, String address) {
        super();
        this.faketckimlikno = faketckimlikno;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.address = address;
    }

}

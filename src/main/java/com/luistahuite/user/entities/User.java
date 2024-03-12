package com.luistahuite.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String name;
    private String email;
    private String password;


}

package com.hriddhi.shiftmanager;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String username;

    @OneToMany(mappedBy = "user")
    private List<Shift> shifts;
}

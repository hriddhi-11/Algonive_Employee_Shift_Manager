package com.hriddhi.shiftmanager;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Users {
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public String getPassword() {
        return password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Shift> shifts;
    @JsonIgnore
    private String password;
}

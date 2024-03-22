package com.example.driveBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private String firstName;
    private String lastName;

}

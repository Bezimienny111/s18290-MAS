package com.example.s18290mas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Employees")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

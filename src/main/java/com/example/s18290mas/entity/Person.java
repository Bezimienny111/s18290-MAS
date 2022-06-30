package com.example.s18290mas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String surname;
    @Getter
    @Setter
    private String telefone;
    @Getter
    @Setter
    private String adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String pass;

    public Person(String name, String surname, String telefone, String adress) {
        this.name = name;
        this.surname = surname;
        this.telefone = telefone;
        this.adress = adress;
    }
}
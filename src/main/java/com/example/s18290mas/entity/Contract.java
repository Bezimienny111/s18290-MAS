package com.example.s18290mas.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Contracts")
public class Contract {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(precision=16, scale=2)
    private float maxAddon;

    @Getter
    @Setter
    @Column(precision=11, scale=0)
    private int countOfRepairs;

    @Getter
    @Setter
    @Column(precision=16, scale=2)
    private float baseSalary;

    @Getter
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_contracts_employee_id"
            )
    )
    private Employee employee;



}

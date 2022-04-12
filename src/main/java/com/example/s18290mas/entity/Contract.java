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
@Getter
@Setter
@Table(name="Contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(precision=16, scale=2)
    private float maxAddon;

    @Getter
    @Column(precision=11, scale=0)
    private int countOfRepairs;

    @Getter
    @Column(precision=16, scale=2)
    private float baseSalary;

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

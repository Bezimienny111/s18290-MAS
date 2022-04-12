package com.example.s18290mas.entity;

import com.example.s18290mas.model.JobPosition;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String email;
    private String department;

    @Enumerated(EnumType.STRING)
    private JobPosition position;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "employee"
    )
    @Builder.Default
    private List<Contract> contracts = new ArrayList<>();



    @ManyToMany(
            cascade = {CascadeType.REMOVE}
    )
    @JoinTable(
            name = "employee_repair",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "repair_id")
    )
    Set<Repair> repairsMade = new HashSet<Repair>();

    public List<Repair> GetListFromSet(){
        return  repairsMade.stream().toList();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }


    public Employee getThis(){
        return  this;
    }

    public int getTotalRepairs(){
        return GetListFromSet().size();
    }
}

package com.example.s18290mas.entity;

import com.example.s18290mas.model.JobPosition;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@DynamicUpdate
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String department;

    @Getter
    @Setter
    private float BonusSalary;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private JobPosition position;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true,
            mappedBy = "employee"
    )
    @Builder.Default
    private List<Contract> contracts = new ArrayList<>();


    @Getter
    @Setter
    @ManyToMany(mappedBy = "repairedB")
    Set<Repair> repairsMade = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return getId().hashCode();
    }


    public List<Contract> getContracts() {
        Collections.sort(contracts, new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        return contracts;
    }

    public List<Repair> GetListFromSet() {
        return repairsMade.stream().toList();
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

    public int getTotalRepairs() {
        return GetListFromSet().size();
    }
    public Employee getThis() {
        return this;
    }

    public Contract getLastContact(){
        return getContracts().get(0);
    }
}



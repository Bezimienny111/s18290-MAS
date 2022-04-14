package com.example.s18290mas.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Repairs")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "employee_repair",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    Set<Employee> repairedB = new HashSet<>();;

    public List<Employee> servisants(){
        return repairedB.stream().toList();
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    @Override
    public int hashCode() {
        return getId().hashCode();
    }

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
        Repair other = (Repair) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }


}

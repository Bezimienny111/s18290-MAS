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
@Getter
@Setter
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @ManyToMany(mappedBy = "repairsMade")
    Set<Employee> repairedB = new HashSet<Employee>();;


    public List<Employee> servisants(){
        return repairedB.stream().toList();
    }


}

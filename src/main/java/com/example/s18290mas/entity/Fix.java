package com.example.s18290mas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fix")
public class Fix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Fix(float price, String description) {
        this.description = description;
        this.price  = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Getter
    @Setter
    private float price;

    @Getter
    @Setter
    private String description;

    @ManyToOne
    @JoinColumn(name="repair_id", nullable=false)
    private Repair repair;


}
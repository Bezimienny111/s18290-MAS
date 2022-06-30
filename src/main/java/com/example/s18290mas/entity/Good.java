package com.example.s18290mas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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
    private String name;
    @Getter
    @Setter
    private int numberOnWarehouse;

    @Getter
    @Setter
    @OneToMany(mappedBy = "good")
    Set<GoodReceipt> goodReceiptSet;

    @Getter
    @Setter
    @OneToMany(mappedBy = "good")
    Set<OwnerGood> ownerGoodSet;
}